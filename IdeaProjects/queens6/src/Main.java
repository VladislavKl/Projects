import java.io.*;
import java.util.StringTokenizer;


public class Main implements Runnable {
    static int k;
    static int  n;
    static int s1,s2;
    static int record;
    static ArrayRezult array;

    static class Pair
    {
        int x;
        int y;
        Pair(int x,int y)
        {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x+" "+y;
        }
    }

    static class Rezult
    {
        int size;
        int capacity;
        Pair array[];
        Rezult()
        {
            size=0;
            capacity = 5;
            array = new Pair[capacity];
        }
        void add(int x,int y)
        {
            if (capacity==size)
            {
                capacity+=5;
                Pair arr[] = new Pair[capacity];
                for (int i=0;i<size;i++)
                {
                    arr[i] = array[i];

                }
                array = arr;
            }
            array[size] = new Pair(x,y);
            size++;
        }
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            for (int i=0;i<size-1;i++)
            {
                sb.append(array[i].toString()+" ");
            }
            sb.append(array[size-1].toString());
            return sb.toString();
        }
    }

    static class ArrayRezult
    {
        int size;
        int capacity;
        Rezult array[];
        ArrayRezult()
        {
            size=0;
            capacity = 5;
            array = new Rezult[capacity];
            for (int i=0;i<capacity;i++)
            {
                array[i] = new Rezult();
            }
        }
        void add(Rezult r)
        {
            if (capacity==size)
            {
                capacity+=5;
                Rezult arr[] = new Rezult[capacity*2];
                for (int i=0;i<size;i++)
                {
                    arr[i] = array[i];

                }
                array = arr;
            }
            array[size] = r;
            size++;
        }
    }

    static void function(int[][]matrix, int rows,int arrRows[], int columns,int arrColumns[], int arrFigures[], int figures,int lastFigure, int left)
    {

        arrFigures[figures] = lastFigure;
        figures++;

        if (left == 0)
        {
            if (figures <= record )
            {
                if (figures<record)
                {
                    array.size = 0;
                    record = figures;
                }
                Rezult rezult = new Rezult();
                for (int i=1;i<figures;i++)
                {
                    rezult.add(arrFigures[i]/s2,arrFigures[i]%s2);

                }
                array.add(rezult);
            }

        }
        else if (left <= (record-figures)*(s1+s2+2*Math.min(s1-1-rows,s2-1-columns)-1-rows-columns))
        {
            int tempLeft;
            int tempMatrix[][] = new int [s1][s2];
            int tempRows, tempColumns;
            int tempArrRows[] = new int[s1];
            int tempArrColumns[] = new int[s2];
            for (int i=lastFigure+1;i<s1*s2;i++)
            {
                if (matrix[i/s2][i%s2] != -1)
                {
                    tempColumns = columns;
                    tempRows = rows;
                    for (int t1=0;t1<s1;t1++)
                    {
                        tempArrRows[t1] = arrRows[t1];
                        for (int t2=0;t2<s2;t2++)
                        {
                            tempMatrix[t1][t2]=matrix[t1][t2];
                        }
                    }
                    for (int t1 = 0;t1<s2;t1++)
                    {
                        tempArrColumns[t1] = arrColumns[t1];
                    }
                    tempLeft=left;
                    if (tempMatrix[i/s2][i%s2]<k)
                    {
                        tempMatrix[i/s2][i%s2]++;
                        tempLeft--;
                        tempArrRows[i/s2]++;
                        tempArrColumns[i%s2]++;
                        if (tempArrRows[i/s2]==s2*k)
                        {
                            tempRows++;
                        }
                        if (tempArrColumns[i%s2]==s1*k)
                        {
                            tempColumns++;
                        }
                    }

                    int j = i/s2+1;
                    while( j<s1 && tempMatrix[j][i%s2]!=-1)
                    {

                        if (tempMatrix[j][i%s2]<k)
                        {
                            tempMatrix[j][i%s2]++;
                            tempLeft--;
                            tempArrRows[j]++;
                            tempArrColumns[i%s2]++;
                            if (tempArrRows[j]==s2*k)
                            {
                                tempRows++;
                            }
                            if (tempArrColumns[i%s2]==s1*k)
                            {
                                tempColumns++;
                            }
                        }
                        j++;

                    }
                    j = i/s2-1;
                    while( j>=0 && tempMatrix[j][i%s2]!=-1)
                    {
                        if ( tempMatrix[j][i%s2]<k)
                        {
                            tempMatrix[j][i%s2]++;
                            tempLeft--;
                            tempArrRows[j]++;
                            tempArrColumns[i%s2]++;
                            if (tempArrRows[j]==s2*k)
                            {
                                tempRows++;
                            }
                            if (tempArrColumns[i%s2]==s1*k)
                            {
                                tempColumns++;
                            }
                        }
                        j--;

                    }
                    j = i%s2+1;
                    while(j<s2 && tempMatrix[i/s2][j]!=-1 )
                    {

                        if ( tempMatrix[i/s2][j]<k)
                        {
                            tempMatrix[i/s2][j]++;
                            tempLeft--;
                            tempArrRows[i/s2]++;
                            tempArrColumns[j]++;
                            if (tempArrRows[i/s2]==s2*k)
                            {
                                tempRows++;
                            }
                            if (tempArrColumns[j]==s1*k)
                            {
                                tempColumns++;
                            }
                        }
                        j++;

                    }
                    j = i%s2-1;
                    while(j>=0 && tempMatrix[i/s2][j]!=-1)
                    {
                        if ( tempMatrix[i/s2][j]<k)
                        {
                            tempMatrix[i/s2][j]++;
                            tempLeft--;
                            tempArrRows[i/s2]++;
                            tempArrColumns[j]++;
                            if (tempArrRows[i/s2]==s2*k)
                            {
                                tempRows++;
                            }
                            if (tempArrColumns[j]==s1*k)
                            {
                                tempColumns++;
                            }
                        }
                        j--;
                    }

                    int j1 = i/s2-1;
                    int j2 = i%s2-1;
                    while(j1>=0 && j2>=0 && tempMatrix[j1][j2]!=-1)
                    {
                        if (tempMatrix[j1][j2]<k)
                        {
                            tempMatrix[j1][j2]++;
                            tempLeft--;
                            tempArrRows[j1]++;
                            tempArrColumns[j2]++;
                            if (tempArrRows[j1]==s2*k)
                            {
                                tempRows++;
                            }
                            if (tempArrColumns[j2]==s1*k)
                            {
                                tempColumns++;
                            }
                        }
                        j1--;
                        j2--;
                    }
                    j1 = i/s2-1;
                    j2 = i%s2+1;
                    while(j1>=0 && j2<s2 && tempMatrix[j1][j2]!=-1)
                    {
                        if (tempMatrix[j1][j2]<k)
                        {
                            tempMatrix[j1][j2]++;
                            tempLeft--;
                            tempArrRows[j1]++;
                            tempArrColumns[j2]++;
                            if (tempArrRows[j1]==s2*k)
                            {
                                tempRows++;
                            }
                            if (tempArrColumns[j2]==s1*k)
                            {
                                tempColumns++;
                            }
                        }
                        j1--;
                        j2++;
                    }

                    j1 = i/s2+1;
                    j2 = i%s2-1;
                    while(j1<s1 && j2>=0 && tempMatrix[j1][j2]!=-1)
                    {

                        if (tempMatrix[j1][j2]<k)
                        {
                            tempMatrix[j1][j2]++;
                            tempLeft--;
                            tempArrRows[j1]++;
                            tempArrColumns[j2]++;
                            if (tempArrRows[j1]==s2*k)
                            {
                                tempRows++;
                            }
                            if (tempArrColumns[j2]==s1*k)
                            {
                                tempColumns++;
                            }
                        }
                        j1++;
                        j2--;
                    }

                    j1 = i/s2+1;
                    j2 = i%s2+1;
                    while(j1<s1 && j2<s2 && tempMatrix[j1][j2]!=-1)
                    {

                        if (tempMatrix[j1][j2]<k)
                        {
                            tempMatrix[j1][j2]++;
                            tempLeft--;
                            tempArrRows[j1]++;
                            tempArrColumns[j2]++;
                            if (tempArrRows[j1]==s2*k)
                            {
                                tempRows++;
                            }
                            if (tempArrColumns[j2]==s1*k)
                            {
                                tempColumns++;
                            }
                        }
                        j1++;
                        j2++;
                    }

                    if (left!=tempLeft)
                        function(tempMatrix,tempRows,tempArrRows,tempColumns,tempArrColumns,arrFigures,figures,i,tempLeft);
                }

            }
        }
    }
    static void toFile(String path) throws IOException
    {
        PrintWriter fw = new PrintWriter(path);
        fw.print(array.size);
        fw.print("\r\n");
        for (int i=0;i<array.size;i++)
        {
            fw.print(array.array[i].toString());
            fw.print("\r\n");
        }
        fw.close();
    }

    public static void main(String[] args) {
        new Thread(null,new Main(),"",64*1024*1024).start();
    }

    public void run()
    {
        try{
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            String line= br.readLine();
            StringTokenizer st = new StringTokenizer(line," ");
            k = Integer.parseInt(st.nextToken());
            line = br.readLine();
            st = new StringTokenizer(line," ");
            s1 = Integer.parseInt(st.nextToken());
            s2 = Integer.parseInt(st.nextToken());
            int matrix[][] = new int[s1][];
            for (int i=0;i<s1;i++)
            {
                matrix[i] = new int[s2];
                for (int j=0;j<s2;j++)
                {
                    matrix[i][j]=0;
                }
            }
            line = br.readLine();
            st = new StringTokenizer(line," ");
            n = Integer.parseInt(st.nextToken());
            int arrRows[] = new int[s1];
            for (int i=0;i<s1;i++)
            {
                arrRows[i]=0;
            }
            int arrColumns[] = new int[s2];
            for (int i=0;i<s2;i++)
            {
                arrColumns[i]=0;
            }
            int rows = 0,columns = 0;
            for(int i = 0;i<n;i++)
            {
                line = br.readLine();
                st = new StringTokenizer(line," ");
                int x = Integer.parseInt(st.nextToken());//-1
                int y = Integer.parseInt(st.nextToken());
                matrix[x][y] = -1;
                arrRows[x]+=k;
                arrColumns[y]+=k;
                if (arrRows[x]==s2*k)
                {
                    rows++;
                }
                if (arrColumns[y]==s1*k)
                {
                    columns++;
                }
            }

            record = s1*s2-n+1;
            array = new ArrayRezult();
            int arrFigures[] = new int[record];

            function(matrix,rows,arrRows,columns,arrColumns,arrFigures,0,-1,(record-1)*k);
            toFile("output.txt");
        }
        catch (IOException e) {}
    }
}
