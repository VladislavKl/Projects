import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PathFinder{

    class Point{
        private int x;
        private int y;

        Point(int x, int y) {
            this.x=x;
            this.y=y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o){
            if(!(o instanceof Point)) return false;
            return (((Point)o).getX()==x) &&(((Point)o).getY()==y);
        }

        @Override
        public String toString(){
            return "x: "+Integer.valueOf(x).toString()+" y:"+Integer.valueOf(y).toString();
        }

    };

    int[][] fillmap = new int[10][10];
    int[][] labyrinth;
    List buf = new ArrayList();

    PathFinder(int[][] labyrinth){
        this.labyrinth = labyrinth;
    }

    void push(Point p, int n){
        if(fillmap[p.getY()][p.getX()]<=n) return;
        fillmap[p.getY()][p.getX()]=n;
        buf.add(p);
    }

    Point pop(){
        if(buf.isEmpty()) return null;
        return (Point)buf.remove(0);
    }

    Point[] find(Point start, Point end){
        int tx=0, ty=0, n = 0, t=0;
        Point p;
        for(int i=0; i<fillmap.length;i++)
            Arrays.fill(fillmap[i], Integer.MAX_VALUE);
        push(start, 0);
        while((p = pop())!=null){
            if(p.equals(end)){
                System.out.print("Hайден путь длины ");
                System.out.println(n);
            }
            // n=длина пути до любой соседней клетки
            n=fillmap[p.getY()][p.getX()]+labyrinth[p.getY()][p.getX()];

            //Пеpебоp 4-х соседних клеток
            if((p.getY()+1<labyrinth.length)&&labyrinth[p.getY()+1][p.getX()]!=0)
                push(new Point(p.getX(), p.getY()+1), n);
            if((p.getY()-1>=0)&&(labyrinth[p.getY()-1][p.getX()]!=0))
                push(new Point(p.getX(), p.getY()-1), n);
            if((p.getX()+1<labyrinth[p.getY()].length)&&(labyrinth[p.getY()][p.getX()+1]!=0))
                push(new Point(p.getX()+1, p.getY()), n);
            if((p.getX()-1>=0)&&(labyrinth[p.getY()][p.getX()-1]!=0))
                push(new Point(p.getX()-1, p.getY()), n);
        }
        if(fillmap[end.getY()][end.getX()]==Integer.MAX_VALUE){
            System.err.println("Пути не существует!");
            return null;
        } else
            System.out.println("Поиск завершен, пpойдемся по пути !!!");
        List path = new ArrayList();
        path.add(end);
        int x = end.getX();
        int y = end.getY();
        n = Integer.MAX_VALUE; // Мы начали заливку из начала пути, значит по пути пpидется идти из конца
        while((x!=start.getX())||(y!=start.getY())){ // Пока не пpидем в начало пути
            //  ищется соседняя
            if(fillmap[y+1][x]<n){tx=x; ty=y+1; t=fillmap[y+1][x];}
            // клетка, содеpжащая
            if(fillmap[y-1][x]<n){tx=x; ty=y-1; t=fillmap[y-1][x];}
            // минимальное значение
            if(fillmap[y][x+1]<n){tx=x+1; ty=y; t=fillmap[y][x+1];}
            if(fillmap[y][x-1]<n){tx=x-1; ty=y; t=fillmap[y][x-1];}
            x = tx;
            y = ty;
            n = t; // Пеpеходим в найденую клетку
            path.add(new Point(x,y));
        }
        //Мы получили путь, только задом наперед, теперь нужно его перевернуть
        Point[] result = new Point[path.size()];
        t = path.size();
        for(Object point: path)
            result[--t] = (Point)point;
        return result;
    }

    public static void main(String[] args){
        int[][] labyrinth = {
                {0,0,0,0,0,0,0,0,0,0},
                {0,1,1,1,1,1,1,1,1,0},
                {0,1,0,0,0,0,1,0,0,0},
                {0,1,0,1,1,1,1,1,1,0},
                {0,1,0,1,1,0,0,0,1,0},
                {0,1,0,1,0,0,1,0,1,0},
                {0,1,0,1,0,1,1,0,1,0},
                {0,1,0,0,0,0,0,0,1,0},
                {0,1,1,1,1,1,1,1,1,0},
                {0,0,0,0,0,0,0,0,0,0}
        };
        PathFinder pathFinder = new PathFinder(labyrinth);
        Point start = pathFinder.new Point(1,1);
        Point end = pathFinder.new Point(6,3);
        Point[] path = pathFinder.find(start,end);

        for(Point p: path)
            System.out.println(p);
    }
}