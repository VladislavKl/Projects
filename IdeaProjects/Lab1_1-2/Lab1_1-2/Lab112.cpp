#include "stdafx.h"
#include <iostream>

using namespace std;

template <typename _F, typename _G, typename _G1, typename _G2, typename _G11, typename _G21>
class AppCompose
{
public:
	AppCompose(_F _f, _G _g, _G1 _g1, _G2 _g2, _G11 _g11, _G21 _g21) : f(_f), g(_g), g1(_g1), g2(_g2), g11(_g11), g21(_g21) { }

	template <typename T>
	auto operator () (T x) { return f(g1(x), g2(x), g(g11(x), g21(x))); }

private:
	_F f;
	_G g;
	_G1 g1;
	_G2 g2;
	_G11 g11;
	_G21 g21;
};

class HisFxGx
{
public:
	template <typename _F, typename _G, typename _G1, typename _G2, typename _G11, typename _G21>
	auto operator () (_F _f, _G _g, _G1 _g1, _G2 _g2, _G11 _g11, _G21 _g21) { return AppCompose<_F, _G, _G1, _G2, _G11, _G21>(_f, _g, _g1, _g2, _g11, _g21); }
};

int main()
{
	auto g1 = [](auto x) {return x * x; };
	auto g2 = [](auto x) {return x * x * x; };
	auto g11 = [](auto x) {return x * x * x * x; };
	auto g21 = [](auto x) {return x * x * x * x * x; };
	auto f = [](auto x, auto y, auto z) { return x * x + y * y + z * z; };
	auto g = [](auto x, auto y) {return 2 * x + 2 * y; };
	HisFxGx xHisFxGx;
	auto hdx = xHisFxGx(f, g, g1, g2, g11, g21);


	auto CrFunc = [](auto _f, auto _g1, auto _g2, auto _g11, auto _g21, auto _g)->auto
	{return[_f, _g1, _g2, _g11, _g21, _g](auto x) {return _f(_g1(x), _g2(x), _g(_g11(x), _g21(x))); }; };
	auto h = CrFunc(f, g1, g2, g11, g21, g);
	cout << h(1) << endl;
	cout << hdx(1) << endl;
	system("pause");
	return 0;
}
