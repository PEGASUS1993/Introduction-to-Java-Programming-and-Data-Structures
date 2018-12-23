class NewRational extends Number implements Comparable<NewRational> {
  // Data fields for numerator and denominator
  private long[] r = new long[2];
  
  /**Default constructor*/
  public NewRational() {
    this(0, 1);
  }
  
  /**Construct a rational with specified numerator and denominator*/
  public NewRational(long numerator, long denominator) {
    long gcd = gcd(numerator, denominator);
    this.r[0] = numerator/gcd;
    this.r[1] = denominator/gcd;
  }
  
  /**Find GCD of two numbers*/
  private long gcd(long n, long d) {
    long t1 = Math.abs(n);
    long t2 = Math.abs(d);
    long remainder = t1%t2;
    
    while (remainder != 0) {
      t1 = t2;
      t2 = remainder;
      remainder = t1%t2;
    }
    
    return t2;
  }
  
  /**Return numerator*/
  public long getNumerator() {
    return r[0];
  }
  
  /**Return denominator*/
  public long getDenominator() {
    return r[1];
  }
  
  /**Add a rational number to this rational*/
  public NewRational add(NewRational secondNewRational) {
    long n = r[0]*secondNewRational.getDenominator() +
    r[1]*secondNewRational.getNumerator();
    long d = r[1]*secondNewRational.getDenominator();
    return new NewRational(n, d);
  }
  
  /**Subtract a rational number from this rational*/
  public NewRational subtract(NewRational secondNewRational) {
    long n = r[0]*secondNewRational.getDenominator()
    - r[1]*secondNewRational.getNumerator();
    long d = r[1]*secondNewRational.getDenominator();
    return new NewRational(n, d);
  }
  
  /**Multiply a rational number to this rational*/
  public NewRational multiply(NewRational secondNewRational) {
    long n = r[0]*secondNewRational.getNumerator();
    long d = r[1]*secondNewRational.getDenominator();
    return new NewRational(n, d);
  }
  
  /**Divide a rational number from this rational*/
  public NewRational divide(NewRational secondNewRational) {
    long n = r[0]*secondNewRational.getDenominator();
    long d = r[1]*secondNewRational.r[0];
    return new NewRational(n, d);
  }
  
  @Override
  public String toString() {
    if (r[1] == 1)
      return r[0] + "";
    else
      return r[0] + "/" + r[1];
  }
  
  /**Override the equals method*/
  public boolean equals(Object parm1) {
    /**@todo: Override this java.lang.Object method*/
    if ((this.subtract((NewRational)(parm1))).getNumerator() == 0)
      return true;
    else
      return false;
  }
  
  /**Override the intValue method*/
  public int intValue() {
    /**@todo: implement this java.lang.Number abstract method*/
    return (int)doubleValue();
  }
  
  /**Override the floatValue method*/
  public float floatValue() {
    /**@todo: implement this java.lang.Number abstract method*/
    return (float)doubleValue();
  }
  
  /**Override the doubleValue method*/
  public double doubleValue() {
    /**@todo: implement this java.lang.Number abstract method*/
    return r[0]*1.0/r[1];
  }
  
  /**Override the longValue method*/
  public long longValue() {
    /**@todo: implement this java.lang.Number abstract method*/
    return (long)doubleValue();
  }
  
  @Override
  public int compareTo(NewRational o) {
    /**@todo: Implement this java.lang.Comparable method*/
    if ((this.subtract((NewRational)o)).getNumerator() > 0)
      return 1;
    else if ((this.subtract((NewRational)o)).getNumerator() < 0)
      return -1;
    else
      return 0;
  }
}
