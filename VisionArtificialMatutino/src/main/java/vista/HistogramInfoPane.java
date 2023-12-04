package vista;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import modelo.Histogram;
import modelo.StringTools;



public class HistogramInfoPane extends JPanel {


  /////////////////////////// INSTANCE VARIABLES ///////////////////////////


  private Histogram histogram;
  private JLabel greyLevel = new JLabel();
  private JLabel freq = new JLabel();
  private JLabel cumFreq = new JLabel();


  //////////////////////////////// METHODS /////////////////////////////////


  public HistogramInfoPane(Histogram theHistogram) {

    histogram = theHistogram;

    // Create a panel containing pixel value

    JPanel greyLevelPane = new JPanel();
    greyLevelPane.add(new JLabel("value"));
    Font fixedFont = new Font("Monospaced", Font.BOLD, 12);
    greyLevel.setFont(fixedFont);
    greyLevel.setForeground(Color.black);
    greyLevelPane.add(greyLevel);
    greyLevelPane.setBorder(BorderFactory.createEtchedBorder());
    add(greyLevelPane);

    // Create a panel containing frequency information

    JPanel frequencyPane = new JPanel();
    frequencyPane.add(new JLabel("freq"));
    freq.setFont(fixedFont);
    freq.setForeground(Color.black);
    frequencyPane.add(freq);
    frequencyPane.add(new JLabel("cum freq"));
    cumFreq.setFont(fixedFont);
    cumFreq.setForeground(Color.black);
    frequencyPane.add(cumFreq);
    frequencyPane.setBorder(BorderFactory.createEtchedBorder());
    add(frequencyPane);

    updateInfo(0, 0);

  }


  // Updates displayed information, given band and grey level value

  public void updateInfo(int band, int value) {
    greyLevel.setText(StringTools.rightJustify(value, 3));
    freq.setText(StringTools.rightJustify(
     histogram.getFrequency(band, value), 6));
    cumFreq.setText(StringTools.rightJustify(
     histogram.getCumulativeFrequency(band, value), 6));
  }


}
