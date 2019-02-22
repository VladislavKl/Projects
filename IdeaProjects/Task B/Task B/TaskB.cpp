// TaskB.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

bool hasFractionalPart(double val) {
	return val - floor(val) > 0;
}

int main()
{
	vector<long long> seq;
	double sum = 0;
	unsigned long long temp = 1;
	while (temp<10000000)
	{
		long long g = temp;
		while (temp != 0)
		{
			sum += temp % 10;
			temp /= 10;
		}
		if (!(hasFractionalPart(3 * g / sum / sum)))
			seq.push_back(3*g/sum/sum);
		sum = 0;
		temp=++g;
	}
	sort(seq.begin(), seq.end());
	unique(seq.begin(), seq.end());
	for (long long i = 1; i < seq.size(); ++i)
		if (seq[i-1] != i)
		{
			cout << i << endl;
			break;
		}
	system("pause");
    return 0;
}

