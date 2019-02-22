#include <jni.h>
#include <stdio.h>
#include <iostream>
#include <vector>
#include <fstream>
#include <set>
#include "Main.h"
JNIEXPORT jint JNICALL Java_Main_dijkstra
  (JNIEnv * env, jobject jobj){


    int n, m;

    std::vector<std::vector<std::pair<int, int> > > arcs;

    cin >> n >> m;
    
    std::vector<std::pair<int, int> > temp;
    
    for (int i = 0; i <= n; i++) {
        arcs.push_back(temp);
    }

    int u, v, w;

    for (int i = 0; i < m; ++i) {
        cin >> u >> v >> w;
        arcs[u - 1].push_back(std::make_pair(v - 1, w));
        arcs[v - 1].push_back(std::make_pair(u - 1, w));
    }


    int distances[n];

    for (int i = 1; i < n; ++i)
        distances[i] = INT_MAX;
    distances[0] = 0;

    std::set<std::pair<int, int> > queue;

    queue.insert(std::make_pair(distances[0], 0));

    while (!queue.empty()) {

        int current = queue.begin()->second;
        
        queue.erase(queue.begin());

        for (int i = 0; i < arcs[current].size(); ++i) {
            if (distances[arcs[current][i].first] > distances[current] + arcs[current][i].second) {
                queue.erase(std::make_pair(distances[arcs[current][i].first], arcs[current][i].first));
                distances[arcs[current][i].first] = distances[current] + arcs[current][i].second;
                queue.insert(std::make_pair(distances[arcs[current][i].first], arcs[current][i].first));
            }
        }
    }

    return distances[n - 1];

}

