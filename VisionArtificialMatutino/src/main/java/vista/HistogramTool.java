package vista;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import modelo.Histogram;
import modelo.HistogramException;
import modelo.ImageDecoder;
import modelo.ImageDecoderException;
import modelo.ImageFile;




public class HistogramTool extends JFrame implements ActionListener {


  /////////////////////////// VARIABLES DE INSTANCIA ///////////////////////////


  private Histogram histogram;          // datos del histograma que se mostrarán
  private HistogramView[] view;         // gráfico del histograma
  private HistogramInfoPane infoPane;   // muestra el valor y la frecuencia
  private JPanel mainPane;              // contiene histograma y panel de información
  private JMenu menu;                   // menú de entrada/salida
  private JFileChooser fileChooser =    // maneja la selección de archivos
   new JFileChooser(System.getProperty("user.dir"));


  //////////////////////////////// MÉTODOS /////////////////////////////////


  public HistogramTool(Histogram theHistogram, String description) {

    super(description);   // etiqueta el marco

    // Crea componentes para mostrar histograma e información.

    histogram = theHistogram;
    infoPane = new HistogramInfoPane(histogram);
    mainPane = new JPanel(new BorderLayout());
    if (histogram.getNumBands() == 3)
      createMultipleViews();   // tres vistas (R, G, B) en un panel con pestañas
    else
      createSingleView();
    mainPane.add(infoPane, BorderLayout.SOUTH);
    setContentPane(mainPane);

    // Agregar una barra de menú para admitir la entrada de imágenes y la salida de histogramas

    JMenuBar menuBar = new JMenuBar();
    menuBar.setBorder(new BevelBorder(BevelBorder.RAISED));
    createFileMenu();
    menuBar.add(menu);
    setJMenuBar(menuBar);

    addWindowListener(new WindowMonitor());

  }


  // Crea un único objeto HistogramView para mostrar un
  // histograma en escala de grises y lo agrega a la GUI

  public void createSingleView() {
    view = new HistogramView[1];
    view[0] = new HistogramView(histogram, infoPane);
    mainPane.add(view[0], BorderLayout.CENTER);
  }


  // Crea tres objetos HistogramView para el rojo y el verde.
  // y bandas azules de un histograma de color, las coloca en un
  // panel con pestañas y agrega el panel con pestañas a la GUI

  public void createMultipleViews() {
    view = new HistogramView[3];
    Color[] bandColor = { Color.red, Color.green, Color.blue };
    String[] tabLabel = { "Red", "Green", "Blue" };
    JTabbedPane views = new JTabbedPane(JTabbedPane.BOTTOM);
    for (int i = 0; i < 3; ++i) {
      view[i] = new HistogramView(histogram, i, infoPane);
      view[i].setColor(bandColor[i]);
      views.add(tabLabel[i], view[i]);
    }
    mainPane.add(views, BorderLayout.CENTER);
  }


  // Crea un menú para admitir la entrada de imágenes y la salida de histogramas
  // y terminación de la solicitud

  public void createFileMenu() {
    menu = new JMenu("File");
    menu.setMnemonic('F');
    String[] itemName = { "Load image", "Save histogram", "Exit" };
    char[] shortcut = { 'L', 'S', 'X' };
    for (int i = 0; i < 3; ++i) {
      JMenuItem item = new JMenuItem(itemName[i], shortcut[i]);
      item.addActionListener(this);
      menu.add(item);
    }
  }


  // Maneja eventos de acción desencadenados por selecciones de menú

  public void actionPerformed(ActionEvent event) {
    String command = event.getActionCommand();
    if (command.startsWith("Load")) {
      loadImage();
      repaint();
    }
    else if (command.startsWith("Save")) {
      saveHistogram();
      repaint();
    }
    else if (command.equals("Exit")) {
      setVisible(false);
      dispose();
      System.exit(0);
    }
  }


  // Carga una nueva imagen, calcula su histograma y actualiza la GUI

  public void loadImage() {

    fileChooser.setDialogTitle("Load image");
    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

      // Carga la imagen y calcula su histograma

      try {
        File file = fileChooser.getSelectedFile();
        ImageDecoder input = 
                ImageFile.createImageDecoder(file.getAbsolutePath());
        BufferedImage image = input.decodeAsBufferedImage();
        histogram.computeHistogram(image);
        setTitle(file.getName());
      }
      catch (ImageDecoderException e) {
        error("Cannot read this image format.");
        return;
      }
      catch (IOException e) {
        error("Failed to read image data.");
        return;
      }
      catch (HistogramException e) {
        error("Cannot compute histogram for this image type.");
        return;
      }

      // Reconstruir GUI

      mainPane.removeAll();
      if (histogram.getNumBands() == 3)
        createMultipleViews();
      else
        createSingleView();
      mainPane.add(infoPane, BorderLayout.SOUTH);
      mainPane.invalidate();
      validate();
      pack();

    }

  }


  // Guarda el histograma actual en un archivo seleccionado por el usuario

  public void saveHistogram() {

    if (histogram.getNumBands() == 0) {
      error("No histogram data to save!");
      return;
    }
    else {
      fileChooser.setDialogTitle("Save histogram");
      if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
        try {
          File file = fileChooser.getSelectedFile();
          if (file.exists()) {
            int response = JOptionPane.showConfirmDialog(this,
             "File will be overwritten!  Are you sure?", "File exists",
             JOptionPane.OK_CANCEL_OPTION);
            if (response != JOptionPane.OK_OPTION)
              return;
          }
          histogram.write(new FileWriter(file));
          fileChooser.rescanCurrentDirectory();
        }
        catch (IOException e) {
          error("Cannot open output file.");
        }
      }
    }

  }


  // Muestra un mensaje de error en un cuadro de diálogo

  public void error(String message) {
    JOptionPane.showMessageDialog(this, message, "Error",
     JOptionPane.ERROR_MESSAGE);
  }


  public static void main(String[] argv) {

    if (argv.length > 0) {
      try {
        ImageDecoder input = ImageFile.createImageDecoder(argv[0]);
        BufferedImage image = input.decodeAsBufferedImage();
        Histogram hist = new Histogram(image);
        HistogramTool histTool = new HistogramTool(hist, argv[0]);
        histTool.pack();
        histTool.setVisible(true);
      }
      catch (Exception e) {
        System.err.println(e);
        System.exit(1);
      }
    }
    else {
      Histogram hist = new Histogram();
      HistogramTool histTool = new HistogramTool(hist, "HistogramTool");
      histTool.pack();
      histTool.setVisible(true);
    }

  }


}

