public class Rectangle extends Figure{

    public Rectangle() {
        super();
        setPoint3(new Point(0,0));
        setPoint4(new Point(0,0));
        rsquare=0;
    }

    public Rectangle(Point _x, Point _y){
        super(_x, _y);
        setPoint3(new Point(_x.getX(),_y.getY()));
        setPoint3(new Point(_y.getX(),_x.getY()));
    }

    public Rectangle(Point _x, Point _y, int _square){
        super(_x, _y);
        setPoint3(new Point(_x.getX(),_y.getY()));
        setPoint3(new Point(_y.getX(),_x.getY()));
        rsquare=_square;
    }

    public void setPoint3(Point _x){
        point3.setX(_x.getX());
        point3.setY(_x.getY());
    }

    public void setPoint4(Point _x){
        point4.setX(_x.getX());
        point4.setY(_x.getY());
    }

    public Point getPoint3() {
        return point3;
    }

    public Point getPoint4() {
        return point4;
    }


    @Override
    public int square(){
        Point temp1=new Point();
        Point temp2=new Point();
        if (Integer.compare(point1.getX(), point3.getX())!=0) {
            temp1.setX(point1.getX());
            temp2.setX(point3.getX());
        }
        else{
            temp1.setX(point1.getX());
            temp2.setX(point4.getX());
        }

        if (Integer.compare(point1.getY(), point3.getY())!=0) {
            temp1.setX(point1.getY());
            temp2.setX(point3.getY());
        }
        else{
            temp1.setX(point1.getY());
            temp2.setX(point4.getY());
        }
        int square=(temp1.getX()-temp2.getX())*(temp1.getY()-temp2.getX());
        if (square<0)
            return -square;
        return square;
    }

    @Override
    public String toString() {
        return super.toString()+"; 3: "+point3.toString()+"; 4: "+point4.toString();
    }

    private Point point3;
    private Point point4;
    private int rsquare;

}
