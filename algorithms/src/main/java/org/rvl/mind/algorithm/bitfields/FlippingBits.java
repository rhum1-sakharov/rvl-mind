package org.rvl.mind.algorithm.bitfields;

public class FlippingBits {

  public long flip(long number) {
    return ~number & 0xffffffffL;
  }

}
