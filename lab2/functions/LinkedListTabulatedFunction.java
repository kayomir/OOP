package functions;
import java.util.Objects;
public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Cloneable {
    static class Node implements Cloneable {
        Node next;
        Node prev;
        double x;
        double y;

        Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString(){
            String str_x = String.valueOf(x);
            String str_y = String.valueOf(y);
            return ("(" + str_x +"; "+ str_y +")");
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            return (o!=null && getClass() == o.getClass() && Double.compare(x, ((Node) o).x) == 0 && Double.compare(y, ((Node) o).y) == 0);
        }
        @Override
        public int hashCode(){
            return Objects.hash(x,y);
        }
        @Override
        public Object clone() throws CloneNotSupportedException {
            Node clone = (Node) super.clone();
            clone.next = null;
            clone.prev = null;
            return clone;
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
    @Override
    public String toString(){
        Node element = head;
        String result = element.toString();
        result += " ";
        while(element != head.prev)
        {
            element = element.next;
            result += element.toString();
            result += " ";
        }
        return result;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        Node elem = head;

        if (this.getCount() == ((LinkedListTabulatedFunction)o).getCount() && this.getClass() == o.getClass()){
            Node o_elem = ((LinkedListTabulatedFunction)o).head;
            do{
                if (!elem.equals(o_elem)) return false;
                elem = elem.next;
                o_elem = o_elem.next;
            }while(elem != head);
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        int result = 0;
        Node elem = head;
        do {
            result = 31 * result + elem.hashCode();
            elem = elem.next;
        }while(elem != head);
        return result;
    }

    @Override
    public Object clone(){
        double[] x_values_clone = new double[count];
        double[] y_values_clone = new double[count];
        Node elem = head;
        int i = 0;
        do {
            x_values_clone[i] = elem.x;
            y_values_clone[i] = elem.y;
            elem = elem.next;
            ++i;
        }while(elem != head);
        LinkedListTabulatedFunction clone = new LinkedListTabulatedFunction(x_values_clone, y_values_clone);
        return clone;
    }
}

