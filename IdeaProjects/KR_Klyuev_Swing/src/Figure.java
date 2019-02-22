import java.util.Comparator;

public class Figure{

    public Figure(){
      point1.setX(0);
      point1.setY(0);
      point2.setX(0);
      point2.setY(0);
    }

    public Figure(Point _x, Point _y){
       point1=_x;
       point2=_y;
    }

    public int square(){
        return 0;
    }

    public void setPoint1(Point _x){
        point1.setX(_x.getX());
        point1.setY(_x.getY());
    }

    public void setPoint2(Point _x){
        point2.setX(_x.getX());
        point2.setY(_x.getY());
    }

    @Override
    public String toString() {
        return "1: "+point1.toString()+"; 2: "+point2.toString();
    }

    protected Point point1;
    protected Point point2;


}
