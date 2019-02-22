import java.io.*;
import java.util.ArrayList;
import java.util.Vector;


class Solution {

    struct lex_compare{
        bool operator () (const std::pair < int,int>&lhs, const std::pair < int,int>&rhs) const{
        if (lhs.first != rhs.first)
            return lhs.first > rhs.first;
        else
            return lhs.second < rhs.second;
    }
    }

    ;


    void colourizer(int start,
                    int end,
                    int idx,
                    std::vector<int>&variants,
                    int qOfColours,
                    std::vector<int>&answer,
                    std::vector<int>&p,
                    std::vector<std::vector<int>>&adjacencyList,
                    int &n,
                    int&minimum,
                    int&maximum) {

        if (minimum <= qOfColours)
            return;

        if (idx == end) {
            maximum = variants[p[start]];
            while (start < end) {
                answer[p[start]] = variants[p[start]];
                if (maximum < variants[p[start]])
                    maximum = variants[p[start]];
                ++start;
            }
            minimum = qOfColours;
            return;
        }

        std::vector < int>painted(n + 1, 0);

        for (auto t : adjacencyList[p[idx]]) {
            painted[variants[t]] = 1;
        }

        for (int i = 1; i <= qOfColours; ++i) {
            if (!painted[i]) {
                variants[p[idx]] = i;
                colourizer(start, end, idx + 1, variants, std::max (qOfColours, i + 1),
                answer, p, adjacencyList, n, minimum, maximum);
                variants[p[idx]] = 0;
            }
        }
    }

    int solution(
            std::vector<int>&answer,
            std::vector<int>&p,
            std::vector<std::vector<int>>&adjacencyList,
            std::vector<std::vector<bool>>&adjacencyMatrix,
            int&n,
            int &minimum) {
        
        
        int maximum;
        std::set < std::pair < int,int>,lex_compare > priority;
        std::vector < int>qOfAdj(n + 1, 0);

        for (int i = 1; i < adjacencyList.size(); ++i) {
            priority.emplace(std::make_pair (adjacencyList[i].size(), i));
        }

        int s = 1;
        for (auto t : priority) {
            p[s++] = t.second;
        }
        int start = 1, end = 2;

        while (end <= n + 1) {
            s = end;
            for (int i = end; i <= n; ++i) {

                if (qOfAdj[p[s]] < qOfAdj[p[i]])
                    s = i;

                if (adjacencyMatrix[p[end - 1]][p[i]])
                    ++qOfAdj[p[i]];
            }

            std::swap (p[end], p[s]);

            if (!qOfAdj[p[end]]) {
                minimum = n + 3;
                std::vector < int>variants(minimum, 0);
                colourizer(start, end, start, variants, 1, answer, p, adjacencyList, n, minimum, maximum);
                start = end;
            }
            ++end;
        }
        return maximum;
    }

    int main() {
        try {
            

            int n = Integer.parseInt(br.readLine());
            int minimum;

            Vector<Vector<Integer>> adjacencyList = new Vector<>(n+1);
            boolean adjacencyMatrix[][] = new boolean[n + 1][n + 1];
            int p[] = new int[n + 1];
            int answer[] = new int[n + 1];


            int x, y;
            for (int i = 0; i < n; ++i) {
                x = Integer.parseInt(br.readLine());
                y = Integer.parseInt(br.readLine());
                adjacencyMatrix[x][y] = adjacencyMatrix[y][x] = true;
                adjacencyList.get(x).add(y);
                adjacencyList.get(y).add(x);
            }
            while ((strLine = br.readLine()) != null) {
                add(Long.parseLong(strLine));
                
            }

            while ((strLine = br.readLine()) != null) {
              add(Long.parseLong(strLine));
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        FileWriter fout;
        try {
            fout = new FileWriter("tst.out");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            if (Math.abs(qOfNodes) >= 2) {
                bufferedWriter.write("NO");
                bufferedWriter.close();
                return;
            }
            if (qOfNodes > 0) {
                if (tree1.solution(tree2)) {
                    bufferedWriter.write("YES\n" + Long.toString(tree1.getAnswer()));
                } else {
                    bufferedWriter.write("NO");
                }
            } else {
                if (tree2.solution(tree1)) {
                    bufferedWriter.write("YES\n" + Long.toString(tree2.getAnswer()));
                } else {
                    bufferedWriter.write("NO");
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
      


        std::cout << solution(answer, p, adjacencyList, adjacencyMatrix, n, minimum) << std::endl;

        for (int i = 1; i < n; ++i)
            std::cout << answer[i] << " ";
        std::cout << answer[n];

        return 0;
    }


}


public class Main {
    public static void main(String[] args) {
        FileWriter fout;
        try {
            fout = new FileWriter("colgraph.out");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            for (int i = 10001; i < 15001; ++i) {
                bufferedWriter.write(Integer.toString(i)+"\n");
            }
                bufferedWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
