public class Point {

    Point(){
        x=0;
        y=0;
    }

    Point(int _x, int _y){
        x=_x;
        y=_y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "x: "+x+"; y: "+y;
    }

    public int x;
    public int y;
}
