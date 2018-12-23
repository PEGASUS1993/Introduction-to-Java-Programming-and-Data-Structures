#include <iostream>
#include <cmath>
using namespace std;

int main()
{
    cout << "Enter year: (e.g., 2008): ";
    int year;
    cin >> year;

    int month;
    cout << "Enter month: 1-12: ";
    cin >> month;
    if (month == 1)
    {
      month = 13;
      year--;
    }
    else if (month == 2)
    {
      month = 14;
      year--;
    }

    int dayOfMonth;
    cout << "Enter the day of the month: 1-31: ";
    cin >> dayOfMonth;

    int k = year % 100;
    int j = static_cast<int>(year / 100.0);

    int dayOfWeek = (int)(dayOfMonth +
      static_cast<int>((month + 1) * 26.0 / 10) +
      k +
      static_cast<int>(k / 4.0) +
      static_cast<int>(j / 4.0) +
      5 * j) % 7;

    if (dayOfWeek == 0)
      cout <<"Day of the week is Saturday" << endl;
    else if (dayOfWeek == 1)
      cout <<"Day of the week is Sunday" << endl;
    else if (dayOfWeek == 2)
      cout <<"Day of the week is Monday" << endl;
    else if (dayOfWeek == 3)
      cout <<"Day of the week is Tuesday" << endl;
    else if (dayOfWeek == 4)
      cout <<"Day of the week is Wedensday" << endl;
    else if (dayOfWeek == 5)
      cout <<"Day of the week is Thursday" << endl;
    else if (dayOfWeek == 6)
      cout <<"Day of the week is Friday" << endl;

    return 0;
}
