package modelo;


/**
 * A simple complex number class.
 *
 * @author Nick Efford
 * @version 1.1 [1999/08/02]
 */

public class Complex {


  /////////////////////////// INSTANCE VARIABLES ///////////////////////////


  /** Real part of number. */
  public float re;

  /** Imaginary part of number. */
  public float im;


  //////////////////////////////// METHODS /////////////////////////////////


  public Complex() {}


  public Complex(float real, float imaginary) {
    re = real;
    im = imaginary;
  }


  public float getMagnitude() {
    return (float) Math.sqrt(re*re + im*im);
  }


  public float getPhase() {
    return (float) Math.atan2(im, re);
  }


  public void setPolar(double r, double theta) {
    re = (float)(r*Math.cos(theta));
    im = (float)(r*Math.sin(theta));
  }


  public String toString() {
    return new String(re + " + " + im + "i");
  }


  public void swapWith(Complex value) {
    float temp = re;
    re = value.re;
    value.re = temp;
    temp = im;
    im = value.im;
    value.im = temp;
  }


}
