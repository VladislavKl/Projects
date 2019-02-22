// TaskC.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <vector>
#include <string>
#include <sstream>   
#include <algorithm>
#include <fstream>

using namespace std;



class Compet
{
public:
	string name;
	char games[100] = { ' ' };
	int sum = 0;
};

int main()
{
	vector <Compet> com;
	string getS, temp;
	int len[28] = { 0 };
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int maxName = 0;
	int maxScore = 0;
	while (!fin.eof())
	{
		getline(fin, getS);
		stringstream stream(getS);
		Compet* tempor1 = new Compet;
		Compet* tempor2 = new Compet;
		stream >> temp;
		tempor1->name = temp;
		if (maxName < temp.size())
			maxName = temp.size();
		stream >> temp >> temp;
		tempor2->name = temp;
		bool f = false;
		for (int i = 0; i < com.size(); ++i)
			if (com[i].name == tempor1->name)
				f = true;
		if (f==false)
			com.push_back(*tempor1);
		f = false;
		for (int i = 0; i < com.size(); ++i)
			if (com[i].name == tempor2->name)
				f = true;
		if (f==false)
			com.push_back(*tempor2);
		delete tempor1;
		delete tempor2;
	}
	sort(com.begin(), com.end(), [](const Compet& a, const Compet& b) {
		return a.name < b.name;
	});
	fin.close();
	ifstream fi("input.txt");
	while (!fi.eof())
	{
		getline(fi, getS);
		stringstream stream(getS);
		Compet* tempor1 = new Compet;
		Compet* tempor2 = new Compet;
		stream >> temp;
		tempor1->name = temp;
		stream >> temp >> temp;
		tempor2->name = temp;
		int i = 0;
		int j = 0;
		for (i = 0; i < com.size(); ++i)
			if (com[i].name == tempor1->name)
				break;
		for (j = 0; j < com.size(); ++j)
			if (com[j].name == tempor2->name)
				break;
		stream >> temp >> temp;
		for (int g = 0; g < temp.size(); ++g)
			if (temp[g] == ':')
				temp[g] = ' ';
		getS = temp;
		stringstream str(getS);
		int A, B;
		str >> A;
		str >> B;
		com[i].games[i] = 'X';
		com[j].games[j] = 'X';
		if (A > B)
		{
			com[i].games[j] = 'W';
			com[i].sum += 3;
			if (maxScore < com[i].sum)
				maxScore = com[i].sum;
			com[j].games[i] = 'L';
		}
		if (A < B)
		{
			com[j].games[i] = 'W';
			com[j].sum += 3;
			if (maxScore < com[j].sum)
				maxScore = com[j].sum;
			com[i].games[j] = 'L';
		}
		if (A == B)
		{
			com[i].games[j] = 'D';
			com[j].games[i] = 'W';
			com[j].sum += 1;
			com[i].sum += 1;
			if (maxScore < com[i].sum)
				maxScore = com[i].sum;
			if (maxScore < com[j].sum)
				maxScore = com[j].sum;
		}
		delete tempor1;
		delete tempor2;
	}
	int b = 0;
	string border;
	border += "+";
	if (com.size() < 10)
		border += "-";
	if (com.size() >= 10 && com.size() < 100)
		border += "--";
	if (com.size() == 100)
		border += "---";
	border += "+";
	for (int i = 0; i < maxName + 1; ++i)
		border += "-";
	border += "+";
	for (int i = 0; i < com.size(); ++i)
		border += "-+";
	for (int i = 0; i < (to_string(maxScore)).size(); ++i)
		border += "-";
	border += "+-+";
	fout << border;
	for (int l = 0; l <com.size(); ++l)
	{
		fout << endl << "|";
		if (com.size() < 10)
			fout << (l + 1) << "|";
		if (com.size() >= 10 && com.size() < 100 && (l + 1) < 10)
			fout << " " << (l + 1) << "|";
		if (com.size() >= 10 && com.size() < 100 && (l + 1) >= 10 && (l + 1) < 100)
			fout << (l + 1) << "|";
		if (com.size() >= 100 && (l + 1) < 10)
			fout << "  " << (l + 1) << "|";
		if (com.size() >= 100 && (l + 1) == 100)
			fout << (l + 1) << "|";
		if (com.size() >= 100 && (l + 1) >= 10 && (l + 1) < 100)
			fout << " " << (l + 1) << "|";
		fout << com[l].name << " ";
		for (int k = 0; k < len[0] - com[l].name.length(); ++k)
			fout << " ";
		fout << "|";
		fout << com[l].name << " ";
		for (int k = 0; k < maxName - com[l].name.length(); ++k)
			fout << " ";
		fout << "|";
		fout << endl;
		fout << border;
	}
	fout.close();
	return 0;
}



