package functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction{
    class Node {
        Node next;
        Node prev;
        double x;
        double y;

        Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private int count;
    private Node head;

    private void addNode(double x, double y) {
        Node newNode = new Node(x, y);
        if (head == null) {
            head = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
        } else {
            Node last_elem = head.prev;
            last_elem.next = newNode;
            newNode.next = head;
            newNode.prev = last_elem;
            head.prev = newNode;
        }
        count++;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) {
            double vrem = xTo;
            xTo = xFrom;
            xFrom = vrem;
        }
        if (xFrom != xTo) {
            double razn = (xTo - xFrom) / (count - 1);
            for (int i = 0; i < count; i++) {
                addNode(xFrom + i * razn, source.apply(xFrom + i * razn));
            }
        } else {
            for (int i = 0; i < count; i++) {
                addNode(xFrom, source.apply(xFrom));
            }
        }
    }

    public int getCount() {
        return count;
    }

    public double leftBound() {
        return head.x;
    }

    public double rightBound() {
        return head.prev.x;
    }

    private Node getNode(int index) {
        if (index == 0) {
            return head;
        }

        Node elem = head;
        for (int i = 0; i <= index; i++) {
            elem = elem.next;
        }
        return elem.prev;
    }

    public double getX(int index) {
        Node elem = getNode(index);
        return elem.x;
    }

    public double getY(int index) {
        Node elem = getNode(index);
        return elem.y;
    }

    public void setY(int index, double value) {
        Node elem = getNode(index);
        elem.y = value;
    }

    public int indexOfX(double x) {
        Node elem = head;
        int index = 0;
        do {
            if (elem.x == x) return index;
            index++;
            elem = elem.next;
        } while (elem != head.prev);
        return -1;
    }

    public int indexOfY(double y) {
        Node elem = head;
        int index = 0;
        do {
            if (elem.y == y) return index;
            index++;
            elem = elem.next;
        } while (elem != head.prev);
        return -1;
    }

    protected int floorIndexOfX(double x) {
        if (head.x > x) return 0;
        else {
            Node elem = head;
            int i = 1;
            do {
                elem = elem.next;
                if (elem.x > x) return (i - 1);
                else if (elem.x == x) return i;
                i++;
            } while (elem != head.prev);
        }
        return count;
    }

    protected double interpolate(double x, int floorIndex) {
        if (head.next == head) {
            return head.y;
        }
        else {
            double leftX = getX(floorIndex - 1);
            double leftY = getY(floorIndex - 1);
            double rightX = getX(floorIndex);
            double rightY = getY(floorIndex);
            return interpolate(x, leftX, rightX, leftY, rightY);
        }

    }

    protected double extrapolateLeft(double x) {
        if (head.next == head) {
            return head.y;
        }
        else return (head.y + (((head.next.y - head.y) / (head.next.x - head.x)) * (x - head.x)));
    }

    protected double extrapolateRight(double x) {
        if (head.next == head) {
            return head.y;
        }
        else
            return (head.prev.prev.y + (((head.prev.y - head.prev.prev.y) / (head.prev.x - head.prev.prev.x)) * (x - head.prev.prev.x)));
    }

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        if (head.next == head) {
            return head.y;
        }
        else return (leftY + (((rightY - leftY) / (rightX - leftX)) * (x - leftX)));
    }
    public double apply(double x) {
        if (x < leftBound()) return extrapolateLeft(x);
        else if (x > rightBound()) return extrapolateRight(x);
        else if (indexOfX(x) != -1) return getY(indexOfX(x));
        else return interpolate(x,floorIndexOfX(x));
    }
}

