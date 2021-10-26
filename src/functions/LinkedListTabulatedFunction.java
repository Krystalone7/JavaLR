package functions;

public class LinkedListTabulatedFunction implements TabulatedFunction {
    private static class FunctionNode{
        private FunctionNode next;
        private FunctionPoint point;
        private FunctionNode prev;

        public FunctionNode(){

        }
        public FunctionNode(FunctionNode next, FunctionNode prev) {
            this.next = next;
            this.prev = prev;
        }
    }
    private FunctionNode head;
    private FunctionNode cur;
    private int len;
    private int pos;
    private FunctionNode getNodeByIndex (int index){
        if (index == pos){
            return cur;
        }
        else if (pos > index){
            while (index!= pos){
                pos--;
                cur = cur.prev;
            }
            return cur;
        }
        else{
            while(index!= pos){
                pos++;
                cur = cur.next;
            }
            return cur;
        }
    }
    public FunctionNode addNodeToTail (FunctionPoint point){
        FunctionNode cr = new FunctionNode(head, head.prev);
        head.prev.next = cr;
        head.prev = cr;
        len++;
        cr.point = point;
        return cr;
    }
    public FunctionNode addNodeByIndex(int index, FunctionPoint point){
        cur = getNodeByIndex(index);
        pos = index;
        FunctionNode cr = new FunctionNode(cur,cur.prev);
        cur.prev.next = cr;
        cur.prev = cr;
        cur = cr;
        len++;
        cr.point = point;
        return cr;
    }
    public FunctionNode deleteNodeByIndex(int index){
        cur = getNodeByIndex(index);
        pos = index;
        FunctionNode cr = cur;
        cur.prev.next = cr.next;
        cur.next.prev = cr.prev;
        cur = cr.next;
        return cr;
    }

    public LinkedListTabulatedFunction(){
        head = new FunctionNode();
        head.prev = head;
        head.next = head;
        len = 0;
        pos = 0;
    }

    public LinkedListTabulatedFunction(FunctionPoint[] a) throws IllegalArgumentException{
        if (a.length < 2){
            throw new IllegalArgumentException();
        }
        else {
            double m = a[0].getX();
            for (FunctionPoint i : a) {
                if (i.getX() <= m){
                    m = i.getX();
                    addNodeToTail(i);
                }
                else{
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    @Override
    public double getLeftDomainBorder() {
        FunctionNode k = getNodeByIndex(0);
        return k.point.x;
    }

    @Override
    public double getRightDomainBorder() {
        FunctionNode k = getNodeByIndex(len-1);
        return k.point.x;
    }

    @Override
    public double getFunctionValue(double x) {
        if (x >= getLeftDomainBorder() && x <= getRightDomainBorder()) {
            for (int i = 0; i < len; i++) {
                if (getNodeByIndex(i).point.x < x && getNodeByIndex(i+1).point.x > x) {
                    System.out.println(i + " " + (i + 1));
                    double k = (getNodeByIndex(i+1).point.y - getNodeByIndex(i).point.y) / (getNodeByIndex(i+1).point.x - getNodeByIndex(i).point.x);
                    double b = (getNodeByIndex(i+1).point.y - k * getNodeByIndex(i+1).point.x);
                    System.out.println(k+ " "+b);
                    return (k*x + b);
                }
            }
        }
        return Double.NaN;
    }

    @Override
    public int getPointsCount() {
        return len;
    }

    @Override
    public void deletePoint(int index) throws FunctionPointIndexOutOfBoundsException {
        deleteNodeByIndex(index);
    }

    @Override
    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {
        addNodeToTail(point);
    }

    @Override
    public FunctionPoint getPoint(int index) throws FunctionPointIndexOutOfBoundsException {
        return getNodeByIndex(index).point;
    }

    @Override
    public void setPoint(int index, FunctionPoint point) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException {
        FunctionNode node = getNodeByIndex(index);
        node.point = point;
    }

    @Override
    public double getPointX(int index) throws FunctionPointIndexOutOfBoundsException {
        if (index < 0 || index >= len){
            throw new FunctionPointIndexOutOfBoundsException();
        }
        else{
            FunctionNode node = getNodeByIndex(index);
            return node.point.x;
        }

    }

    @Override
    public void setPointX(int index, double x) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException {
        if (index < 0 || index >= len){
            throw new FunctionPointIndexOutOfBoundsException();
        }
        else{
            FunctionNode node = getNodeByIndex(index);
            node.point.x = x;
        }
    }

    @Override
    public double getPointY(int index) {
        if (index < 0 || index >= len){
            throw new FunctionPointIndexOutOfBoundsException();
        }
        else{
            FunctionNode node = getNodeByIndex(index);
            return node.point.y;
        }
    }

    @Override
    public void setPointY(int index, double y) {
        if (index < 0 || index >= len){
            throw new FunctionPointIndexOutOfBoundsException();
        }
        else{
            FunctionNode node = getNodeByIndex(index);
            node.point.y = y;
        }
    }


}
