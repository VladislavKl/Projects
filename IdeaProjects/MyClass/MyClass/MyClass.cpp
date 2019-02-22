#include"stdafx.h"
#include <iostream>
#include <utility>
#include <exception>
#include <string>
#include <iostream>


using namespace std;

#define DEFSET 100

template <class T>
class Set
{
public:
	T *SetPtr;
	int MemSize;
	int NumMembers;

	Set(initializer_list<T> init) {
		SetPtr = new T[DEFSET];
		MemSize = DEFSET;
		NumMembers = 0;
		for (auto&& v : init)
			insert(forward<decltype(v)>(v));
	}

	template<typename TI>
	friend istream& operator >> (istream& stream, Set &ob);

	template<typename TI>
	friend ostream& operator << (ostream& stream, Set &ob);

	void insert(T member);
	void remove(T member);
	void remove(int o) {
		remove(SetPtr[o]);
	}
	int find(T member);
	int ismember(T member);
	void clear();
	int size();
	Set();
	Set(int size);
	Set(Set&& ob);
	Set(const Set& ob);

	~Set() { delete SetPtr; }
	Set<T>& operator = (const Set<T> &ob);
	Set<T>& operator = (Set<T> &&ob);
	Set<T>& operator + (T member);
	Set<T> operator + (Set<T> &ob);
	Set<T> operator - (T member);
	Set<T> operator - (Set<T> &ob);
	int operator == (Set<T> &ob);
	int operator != (Set<T> &ob);
	int operator < (Set<T> &ob);
	int operator > (Set<T> &ob);
	int operator <= (Set<T> &ob);
	int operator >= (Set<T> &ob);
	int operator [] (int x);
	operator int() { return NumMembers; }
	struct iterator
	{
		T *ptr;
		iterator(T* ptr_ = 0) : ptr(ptr_) {}
		T& operator *() { return *ptr; }
		T* operator ->() { return ptr; }
		T* operator ++() { return ++ptr; }
		T* operator ++(int) { return ptr++; }
		T* operator --() { return --ptr; }
		T* operator --(int) { return ptr--; }
		bool operator==(const iterator& other) const { return ptr == other.ptr; }
		bool operator!=(const iterator& other) const { return !(*this == other); }
		bool operator <  (const iterator& other) const { return (ptr < other.ptr); }
		bool operator <= (const iterator& other) const { return (ptr <= other.ptr); }
		bool operator >  (const iterator& other) const { return (ptr > other.ptr); }
		bool operator >= (const iterator& other) const { return (ptr >= other.ptr); }
		T* operator += (int a) { ptr += a; return ptr; }
		T* operator -= (int a) { ptr -= a; return ptr; }
	};
	iterator begin() { return SetPtr; }
	iterator end() { return SetPtr + NumMembers; }
	iterator at(int x) { return SetPtr + x; }

	template<class... Args>
	void emplace(Args&&... args)
	{
	T member(args...);
	if (NumMembers == MemSize)
	{
		T* tmp = new T[MemSize + 10];
		for (int i = 0; i< NumMembers; i++)
			tmp[i] = SetPtr[i];
		delete[]SetPtr;
		SetPtr = tmp;
	}
	if (!ismember(member))
	{
		int i = 0;
		while (i<NumMembers && SetPtr[i]<member) i++;
		for (int j = NumMembers - 1; j >= i; j--)
			SetPtr[j + 1] = SetPtr[j];
		SetPtr[i] = member;
		NumMembers++;
	}
	}


};

template <class T> Set<T> ::Set()
{
	SetPtr = new T[DEFSET];
	if (!SetPtr)
	{
		cout << "?????? ??? ?????????? ??????? ? ??????.\n";
		exit(1);
	}
	NumMembers = 0;
	MemSize = DEFSET;
}

template <class T> Set<T> ::Set(int size)
{
	SetPtr = new T[size];
	if (!SetPtr)
	{
		cout << "?????? ??? ?????????? ??????? ? ??????.\n";
		exit(1);
	}
	NumMembers = 0;
	MemSize = size;
}

template <class T> Set<T> ::Set(Set<T> &&ob)
{
	MemSize = ob.MemSize;
	SetPtr = new T[MemSize];
	if (!SetPtr)
	{
		cout << "?????? ????????? ?????? \n";
		exit(1);
	}
	NumMembers = ob.NumMembers;
	for (int i = 0; i<NumMembers; i++)
		SetPtr[i] = ob.SetPtr[i];
	ob.NumMembers = 0;
	ob.MemSize = 0;
	ob.SetPtr = nullptr;
}

template <class T> Set<T> ::Set(const Set<T>& ob)
{
	MemSize = ob.MemSize;
	SetPtr = new T[MemSize];
	if (!SetPtr)
	{
		cout << "?????? ????????? ?????? \n";
		exit(1);
	}
	NumMembers = ob.NumMembers;
	for (int i = 0; i<NumMembers; i++)
		SetPtr[i] = ob.SetPtr[i];
}

template <class T> int Set <T> ::find(T member)
{
	for (int i = 0; i< NumMembers; i++)
		if (SetPtr[i] == member)
			return i;
	return -1;
}

template <class T> int Set <T> ::size()
{
	return NumMembers;
}

template <class T> void Set <T> ::clear()
{
	NumMembers = 0;
}

template <class T> int Set <T> ::ismember(T member)
{
	if (find(member) != -1) return 1;
	else return 0;
}

template <class T> void Set <T> ::insert(T member)
{
	if (NumMembers == MemSize)
	{
		T* tmp = new T[MemSize + 10];
		for (int i = 0; i< NumMembers; i++)
			tmp[i] = SetPtr[i];
		delete[]SetPtr;
		SetPtr = tmp;
	}
	if (!ismember(member))
	{
		int i = 0;
		while (i<NumMembers && SetPtr[i]<member) i++;
		for (int j = NumMembers - 1; j >= i; j--)
			SetPtr[j + 1] = SetPtr[j];
		SetPtr[i] = member;
		NumMembers++;
	}
}


template <class T>
void Set<T> ::remove(T member)
{
	int loc = find(member);
	if (loc != -1)
	{
		for (; loc<NumMembers - 1; loc++)
			SetPtr[loc] = SetPtr[loc + 1];
		NumMembers--;
	}
}

template <class T> istream& operator >> (istream& stream, Set<T> &ob)
{
	T member;
	int k;
	cout << "??????? ????????? ??????????? ";
	cin >> k;
	cout << "??????? ???????? ????????? \n";
	for (int i = 0; i< k; i++)
	{
		cout << i + 1 << " --> ";
		stream >> member;
		ob = ob + member;
	}
	return stream;
}

template <class T> ostream& operator << (ostream& stream, Set<T> &ob)
{
	stream << "{ ";
	for (int i = 0; i<ob.NumMembers; i++)
		stream << ob.SetPtr[i] << " ";
	stream << " }" << endl;
	return stream;
}

template <class T> Set<T>& Set<T>:: operator = (Set<T>&& ob)
{
	if (ob.NumMembers == 0)
		throw 1;
	if (SetPtr == ob.SetPtr) return *this;
	MemSize = ob.MemSize;
	delete[]SetPtr;
	SetPtr = new T[MemSize];
	NumMembers = ob.NumMembers;
	for (int i = 0; i<NumMembers; i++)
		SetPtr[i] = ob.SetPtr[i];
	ob.NumMembers = 0;
	ob.MemSize = 0;
	ob.SetPtr = nullptr;
	return *this;
}

template <class T> Set<T>& Set<T>:: operator = (const Set<T>& ob)
{
	if (ob.NumMembers == 0)
		throw 1;
	if (SetPtr == ob.SetPtr) return *this;
	MemSize = ob.MemSize;
	delete[]SetPtr;
	SetPtr = new T[MemSize];
	NumMembers = ob.NumMembers;
	for (int i = 0; i<NumMembers; i++)
		SetPtr[i] = ob.SetPtr[i];
	return *this;
}

template <class T> Set<T>& Set<T>:: operator + (T member)
{
	insert(member);
	return *this;
}

template <class T> Set<T> Set<T>::operator + (Set<T> &ob)
{
	Set<T>	temp(MemSize + ob.MemSize);
	for (int i = 0; i<NumMembers; i++)
		temp.insert(SetPtr[i]);
	for (int i = 0; i<ob.NumMembers; i++)
		temp.insert(ob.SetPtr[i]);
	return temp;
}

template <class T> Set<T> Set<T>:: operator - (T member)
{
	Set<T> temp(*this);
	temp.remove(member);
	return temp;
}

template <class T> Set<T> Set<T>:: operator - (Set<T>& ob)
{
	Set<T> temp(*this);
	for (int i = 0; i < ob.NumMembers; i++)
		if (ismember(ob.SetPtr[i]))
			temp.remove(ob.SetPtr[i]);
	return temp;
}

template <class T> int Set<T>:: operator < (Set<T> &ob)
{
	for (int i = 0; i<NumMembers; i++)
		if (!ob.ismember(SetPtr[i]))
			return 0;
	return 1;
}

template <class T> int Set<T>:: operator >= (Set<T> &ob)
{
	for (int i = 0; i<NumMembers; i++)
		if (!ob.ismember(SetPtr[i]))
			return 1;
	return 0;
}

template <class T> int Set<T>:: operator > (Set<T> &ob)
{
	for (int i = 0; i<ob.NumMembers; i++)
		if (!ismember(ob.SetPtr[i]))
			return 0;
	return 1;
}

template <class T> int Set<T>:: operator <= (Set<T> &ob)
{
	for (int i = 0; i<ob.NumMembers; i++)
		if (!ismember(ob.SetPtr[i]))
			return 1;
	return 0;
}

template <class T> int Set<T>:: operator == (Set<T> &ob)
{
	if (NumMembers != ob.NumMembers) return 0;
	return *this < ob;
}

template <class T> int Set<T>:: operator != (Set<T> &ob)
{
	return !(*this == ob);
}

template <class T> int Set<T>:: operator [] (int x)
{
	if (x >= NumMembers)
		throw 2;
	return this->SetPtr[x];
}

class MyClass {
public:
	int a, b, c;
	MyClass(){}
	MyClass(int _a, int _b, int _c) : a(_a), b(_b), c(_c) {}
	friend ostream& operator <<(ostream& fout, MyClass& g)
	{
		fout << g.a <<" "<< g.b <<" "<< g.c << endl;
		return fout;
	}
	bool operator < (MyClass& b) {
		if (a < b.a)
			return true;
		return false;
	}
	bool operator ==(MyClass& b) {
		if (a == b.a)
			return true;
		return false;
	}
};

int main()
{
	Set<float> a;
	a.insert(50);
	a.insert(200);
	a.insert(12);
	a.insert(34);
	a.insert(78);
	cout << a;
	system("pause");
	return 0;
}