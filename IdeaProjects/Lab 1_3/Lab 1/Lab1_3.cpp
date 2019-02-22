#include "stdafx.h"
#include <iostream>
#include <tuple>

using namespace std;


int g1(int a){
	return a;
}

int g2(int a){
	return a * a;
}

int g11(int a){
	return a * 2;
}

int g12(int a){
	return a * 3;
}

int g21(int a){
	return a * 4;
}

int g22(int a){
	return a * 5;
}

int g(int a, int b){
	return a + b;
}


int f1(int a, int b, int c){
	return a + b + c;
}

int f2(int a, int b, int c, int d){
	return a + b + c + d;
}



template<typename F, typename G1, typename G2, typename G, typename G11, typename G12, typename ... Ff>
class TupleComposer
{
public:
	F f;
	G1 g1;
	G2 g2;
	G g;
	G11 g11;
	G12 g12;
	tuple<Ff ...> ff;
public:
	TupleComposer(F fo, G1 g1o, G2 g2o, G go, G11 g11o, G12 g12o, Ff... fs) : f(fo), g1(g1o), g2(g2o), g(go), g11(g11o), g12(g12o), ff(fs ...) { ; }

	template <typename Arg>
	decltype(auto) operator()(Arg x) const{
		return MakeCall(x, make_index_sequence<sizeof...(Ff) / 2>());
	}
private:

	template<typename Arg, typename TypeItem, int ... Idxs>
	decltype(auto) MakeCall(Arg x, integer_sequence<TypeItem, Idxs...> const&) const{
		return f(g1(x), g2(x), g(g11(x), g12(x)), g(get<Idxs * 2>(ff)(x), get<Idxs * 2 + 1>(ff)(x))...);
	}
};

template<typename F, typename G1, typename G2, typename G, typename G11, typename G12, typename ... Ff>
TupleComposer<F, G1, G2, G, G11, G12, Ff ...> TupleCompose(F f, G1 g1, G2 g2, G g, G11 g11, G12 g12, Ff ... fs){
	return TupleComposer<F, G1, G2, G, G11, G12, Ff...>(f, g1, g2, g, g11, g12, fs ...);
}

int main()
{
	auto h1 = TupleCompose(f2, g1, g2, g, g11, g12, g21, g22);
	auto x3 = h1(3);
	cout << x3 << endl;
	system("pause");
	return 0;
}