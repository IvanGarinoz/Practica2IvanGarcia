package Practicas;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.ArrayList;

import static java.awt.Font.PLAIN;

public class Practica2IvanGarcia extends JFrame {
    CardLayout layoutTarjetas;
    JPanel panelTarjetas, panelInferior, panelSuperior, tarjetas[] = new JPanel[5];

    JButton botonesTarjetas[] = new JButton[2], botonChooser, botonFinalizar;
    String botTarjTitulos[] = {"Atrás", "Siguiente"};
    String nombreTarjetas[] = {"tarjeta1", "tarjeta2", "tarjeta3", "tarjeta4", "tarjeta5"};

    JTextPane panelTarj1, panelTarj2[] = new JTextPane[2], panelTarj3, panelTarj4, panelTarj5;

    JLabel nombreEmailLabel[] = new JLabel[2], condicionesEmail[] = new JLabel[3], contrasenaLabels[] = new JLabel[2],
           condicionesContrasena[] = new JLabel[6];
    JTextField nombreEmail[] = new JTextField[2], nombreFichero;
    JPasswordField contrasena[] = new JPasswordField[2];

    String textoPanelTarj2[] = {"Introduzca el nombre de usuario que prefiera"
                                +" y un email que cumpla con las condiciones:",
                                "Introduzca una contraseña que cumpla con las condiciones:"};
    String textoCondicionesEmail[] = {"· Debe contener solo un arroba","· Debe empezar por una letra",
            "· Debe tener un dominio válido"},
           textoCondicionesContrasena[] = {"· Debe tener entre 8 y 16 carácteres","· Debe contener al menos un dígito",
                   "· Debe contener al menos una mayúscula","· Debe contener al menos una minúscula",
                   "· Al menos un carácter que no sea letra o número",
                   "· La contraseña debe ser la misma en ambos campos"};
    String textoNombreEmail[] = {"Nombre de usuario:", "Email:"},
            textoContrasena[] = {"Contraseña:", "Repita su contraseña:"};

    JLabel comboLabels[] = new JLabel[2], labelPais, labelsTarj4[] = new JLabel[3], labelErrorFichero, labelNombreEmail,
            labelContrasena;
    JComboBox comboBox[] = new JComboBox[2];
    String textoCombo[] = {"Pais:", "Región:"},
            textoLabelsTarj4[] = {"¿Desea guardar los datos en un fichero?", "Escoga el nombre del fichero:"};
    String rutaProvincias = "C:\\Users\\ivanb\\Carpetas escritorio\\Idea\\Ejemplo\\src\\Ficheros\\provincias.txt";
    String rutaEstados = "C:\\Users\\ivanb\\Carpetas escritorio\\Idea\\Ejemplo\\src\\Ficheros\\estados.txt";
    String rutaPaises = "C:\\Users\\ivanb\\Carpetas escritorio\\Idea\\Ejemplo\\src\\Ficheros\\paises.txt";

    ArrayList<String> estados, paises, provincias = new ArrayList();

    JCheckBox checkBox;

    JFileChooser fileChooser;

    String nombreSeleccionado, emailSeleccionado, contrasenaSeleccionada, regionSeleccionada,
            paisSeleccionado;

    boolean guardarExitoso = false, guardarCheckBox = false;

    int coorx1 = 30,coory1 = 30, coory2 = 95, coory3 = 250, coory4 = 33, coory5 = 207,
        coory6 = 53, coory7 = 227, coorx2 = 50, coory8 = 30, coorx3 = 110, width1 = 320, tamano1[] = {60, 50};

    public Practica2IvanGarcia() throws IOException {
        initPantalla(); //Creamos el JFrame
        initPaneles(); //Creamos los dos paneles principales, el del cardLayout y uno inferior para los botones
        initTarjetas(); //Creamos las 5 tarjetas/ventanas para el cardLayout
        initTarjeta1(); //Creamos todos los componentes que se visualizaran en la primera tarjeta
        initTarjeta2(); //Creamos todos los componentes que se visualizaran en la segunda tarjeta
        initTarjeta3(); //Creamos todos los componentes que se visualizaran en la tercera tarjeta
        initTarjeta4(); //Creamos todos los componentes que se visualizaran en la cuarta tarjeta
        initTarjeta5(); //Creamos todos los componentes que se visualizaran en la quinta tarjeta
        initBotones(); //Creamos los tres botones necesarios del panel inferior
        setVisible(true);
    }

    public void initPantalla(){
        setTitle("Práctica 2");
        setSize(600,600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initPaneles(){
        //Creamos el panel donde se añadirán todas las tarjetas
        panelSuperior = new JPanel();
        panelSuperior.setBackground(Color.lightGray);
        panelSuperior.setPreferredSize(new Dimension(600, 74));
        panelSuperior.setLayout(null);
        add(panelSuperior,BorderLayout.NORTH);

        //Creamos el panel donde se añadirán los botones de siguiente, atrás y finalizar
        panelInferior = new JPanel();
        panelInferior.setBackground(Color.LIGHT_GRAY);
        panelInferior.setPreferredSize(new Dimension(600, 70));
        panelInferior.setLayout(null);
        add(panelInferior,BorderLayout.SOUTH);

        //Creamos el cardLayout y lo añadimos al panel principal
        layoutTarjetas = new CardLayout();

        panelTarjetas = new JPanel();
        panelTarjetas.setLayout(layoutTarjetas);
        add(panelTarjetas);
    }

    public void initTarjetas(){
        //Creamos las 5 tarjetas que se visualizarán y las añadimos al cardLayout
        for (int i = 0; i < 5; i++){
            tarjetas[i] = new JPanel();
            tarjetas[i].setBackground(Color.LIGHT_GRAY);
            tarjetas[i].setLayout(null);
            panelTarjetas.add(tarjetas[i], nombreTarjetas[i]);
        }
    }

    public void initTarjeta1(){
        //Creamos el texto de bienvenida y lo añadimos al JTextPane que creamos posteriormente
        String texto = "¡Hola! ¡Bienvenido a Smart Cube!\n"
                +"Para poder usar la aplicación deberá crearse una cuenta SmartCube.\n\n"
                +"Para ello debe proporcionar:\n"
                +"  · Un nombre de usuario.\n"
                +"  · Un email válido.\n"
                +"  · Una contraseña válida.\n"
                +"  · El lugar donde reside.\n\n"
                +"Por último, después de introducir los datos podrá guardarlos"
                +" en un archivo de texto en la ruta que desee.";

        panelTarj1 = new JTextPane();
        panelTarj1.setBounds(110,30,400,290);
        panelTarj1.setBackground(Color.LIGHT_GRAY);
        panelTarj1.setEditable(false);
        panelTarj1.setFont(new Font("MONOSPACED", PLAIN, 16));
        panelTarj1.setForeground(Color.BLACK);
        panelTarj1.setText(texto);
        tarjetas[0].add(panelTarj1);
    }

    public void initTarjeta2(){
        for (int i = 0; i < 2; i++){
            //Creamos dos JTextPane con la guía de los datos a introducir
            panelTarj2[i] = new JTextPane();
            panelTarj2[i].setBackground(Color.LIGHT_GRAY);
            panelTarj2[i].setEditable(false);
            panelTarj2[i].setFont(new Font("MONOSPACED", PLAIN, 13));
            panelTarj2[i].setForeground(Color.BLACK);
            panelTarj2[i].setText(textoPanelTarj2[i]);
            panelTarj2[i].setBounds(30,coory1,300,tamano1[i]);
            tarjetas[1].add(panelTarj2[i]);
            coory1 += 175;

            //Creamos dos labels para mostrar donde introducir el nombre de usuario y el email
            nombreEmailLabel[i] = new JLabel(textoNombreEmail[i]);
            nombreEmailLabel[i].setBackground(Color.LIGHT_GRAY);
            nombreEmailLabel[i].setFont(new Font("MONOSPACED", PLAIN, 13));
            nombreEmailLabel[i].setForeground(Color.BLACK);
            nombreEmailLabel[i].setBounds(375,coory4,150,20);
            tarjetas[1].add(nombreEmailLabel[i]);
            coory4 += 50;

            //Creamos dos labels para mostrar donde introducir la contraseña a crear
            contrasenaLabels[i] = new JLabel(textoContrasena[i]);
            contrasenaLabels[i].setBackground(Color.LIGHT_GRAY);
            contrasenaLabels[i].setFont(new Font("MONOSPACED", PLAIN, 13));
            contrasenaLabels[i].setForeground(Color.BLACK);
            contrasenaLabels[i].setBounds(375,coory5,170,20);
            tarjetas[1].add(contrasenaLabels[i]);
            coory5 += 50;

            //Creamos dos textFields para introducir el email y el nombre de usuario
            nombreEmail[i] = new JTextField();
            nombreEmail[i].setBackground(Color.LIGHT_GRAY);
            nombreEmail[i].setFont(new Font("MONOSPACED", PLAIN, 13));
            nombreEmail[i].setForeground(Color.BLACK);
            nombreEmail[i].setBorder(new LineBorder(Color.BLACK));
            nombreEmail[i].setBounds(375,coory6,170,20);
            tarjetas[1].add(nombreEmail[i]);
            coory6 += 50;

            //Creamos dos passwordFields para introducir la contraseña
            contrasena[i] = new JPasswordField();
            contrasena[i].setBackground(Color.LIGHT_GRAY);
            contrasena[i].setFont(new Font("MONOSPACED", PLAIN, 13));
            contrasena[i].setForeground(Color.BLACK);
            contrasena[i].setBorder(new LineBorder(Color.BLACK));
            contrasena[i].setBounds(375,coory7,170,20);
            tarjetas[1].add(contrasena[i]);
            coory7 += 50;
        }

        for (int i = 0; i < 3; i++){
            //Creamos los labels con las condiciones específicas que debe tener el email
            condicionesEmail[i] = new JLabel(textoCondicionesEmail[i]);
            condicionesEmail[i].setBackground(Color.LIGHT_GRAY);
            condicionesEmail[i].setFont(new Font("MONOSPACED", PLAIN, 10));
            condicionesEmail[i].setForeground(Color.BLACK);
            condicionesEmail[i].setBounds(30,coory2,300,20);
            tarjetas[1].add(condicionesEmail[i]);
            coory2 += 25;
        }

        for (int i = 0; i < 6; i++){
            //Creamos los labels con las condiciones específicas que debe tener la contraseña
            condicionesContrasena[i] = new JLabel(textoCondicionesContrasena[i]);
            condicionesContrasena[i].setBackground(Color.LIGHT_GRAY);
            condicionesContrasena[i].setFont(new Font("MONOSPACED", PLAIN, 10));
            condicionesContrasena[i].setForeground(Color.BLACK);
            condicionesContrasena[i].setBounds(30,coory3,335,20);
            tarjetas[1].add(condicionesContrasena[i]);
            coory3 += 25;
        }

        //Creamos un label que se visualiza cuando no se han rellenado los campos de nombre de usuario y email
        labelNombreEmail = new JLabel("· Debe rellenar ambos campos");
        labelNombreEmail.setBackground(Color.LIGHT_GRAY);
        labelNombreEmail.setFont(new Font("MONOSPACED", PLAIN, 10));
        labelNombreEmail.setForeground(Color.RED);
        labelNombreEmail.setBounds(30,coory2,300,20);
        tarjetas[1].add(labelNombreEmail);
        labelNombreEmail.setVisible(false);

        //Creamos un label que se visualiza cuando no se han rellenado los campos de la contraseña
        labelContrasena = new JLabel("· Debe rellenar ambos campos");
        labelContrasena.setBackground(Color.LIGHT_GRAY);
        labelContrasena.setFont(new Font("MONOSPACED", PLAIN, 10));
        labelContrasena.setForeground(Color.RED);
        labelContrasena.setBounds(30,coory3,300,20);
        tarjetas[1].add(labelContrasena);
        labelContrasena.setVisible(false);
    }

    public void initTarjeta3() throws IOException {
        //Creamos un JTextPane con la guía de los datos a introducir
        String texto = "Por último debera proporcionar su lugar de residencia actual.";
        panelTarj3 = new JTextPane();
        panelTarj3.setBounds(145,30,330,60);
        panelTarj3.setBackground(Color.LIGHT_GRAY);
        panelTarj3.setEditable(false);
        panelTarj3.setFont(new Font("MONOSPACED", PLAIN, 16));
        panelTarj3.setForeground(Color.BLACK);
        panelTarj3.setText(texto);
        tarjetas[2].add(panelTarj3);

        //Indicamos la ruta de los ficheros a leer para introducir los datos en los comboBox
        paises = leerFichero(rutaPaises);
        estados = leerFichero(rutaEstados);
        provincias = leerFichero(rutaProvincias);

        for (int i = 0; i < 2; i++){
            //Creamos dos labels para mostrar cuál es cada comboBox
            comboLabels[i] = new JLabel(textoCombo[i]);
            comboLabels[i].setBackground(Color.LIGHT_GRAY);
            comboLabels[i].setFont(new Font("MONOSPACED", PLAIN, 13));
            comboLabels[i].setForeground(Color.BLACK);
            comboLabels[i].setBounds(coorx2,125,100,20);
            tarjetas[2].add(comboLabels[i]);

            //Creamos los dos comboBox
            comboBox[i] = new JComboBox();
            comboBox[i].setBackground(Color.LIGHT_GRAY);
            comboBox[i].setFont(new Font("MONOSPACED", PLAIN, 13));
            comboBox[i].setForeground(Color.BLACK);
            comboBox[i].setBounds(coorx2,145,200,30);
            tarjetas[2].add(comboBox[i]);

            coorx2 += 289;
        }

        //Creamos un label que se visualiza cuando no se han seleccionado alguno de los dos comboBox
        labelPais = new JLabel("Debe seleccionar un país y una región.");
        labelPais.setBackground(Color.LIGHT_GRAY);
        labelPais.setFont(new Font("MONOSPACED", PLAIN, 13));
        labelPais.setForeground(Color.RED);
        labelPais.setBounds(145,200,305,20);
        tarjetas[2].add(labelPais);
        labelPais.setVisible(false);

        comboBox[1].setVisible(false);
        //Añadimos la información del fichero al comboBox de de países
        for (String pais: paises){
            comboBox[0].addItem(pais);
        }
        comboBox[0].addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //Creamos un itemListener del primer comboBox para que dependiendo de que país se pulse se añadan
                //las regiones de uno u otro país en el segundo combobox a través de dos ficheros
                if (comboBox[0].getSelectedIndex()==0){
                    for (String provincia: provincias){
                        comboBox[1].removeItem(provincia);
                    }
                    for (String estado: estados){
                        comboBox[1].removeItem(estado);
                    }
                    comboBox[1].setVisible(false);
                }
                if (comboBox[0].getSelectedIndex()==1){
                    for (String provincia: provincias){
                        comboBox[1].removeItem(provincia);
                    }
                    for (String estado: estados){
                        comboBox[1].removeItem(estado);
                    }
                    for (String provincia: provincias){
                        comboBox[1].addItem(provincia);
                    }
                    comboBox[1].setVisible(true);
                }
                if (comboBox[0].getSelectedIndex()==2){
                    for (String provincia: provincias){
                        comboBox[1].removeItem(provincia);
                    }
                    for (String estado: estados){
                        comboBox[1].removeItem(estado);
                    }
                    for (String estado: estados){
                        comboBox[1].addItem(estado);
                    }
                    comboBox[1].setVisible(true);
                }
            }
        });
    }

    private ArrayList<String> leerFichero(String ruta) throws IOException {
        //Creamos la función que lee el fichero para lso comboBox
        ArrayList<String> array = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(ruta));
        String linea;
        while ((linea = bufferedReader.readLine()) != null){
            array.add(linea);
        }
        return array;
    }

    public void initTarjeta4(){
        for (int i = 0; i < 2; i++){
            //Creamos los labels necesarios para la cuarta tarjeta
            labelsTarj4[i] = new JLabel(textoLabelsTarj4[i]);
            labelsTarj4[i].setBackground(Color.LIGHT_GRAY);
            labelsTarj4[i].setFont(new Font("MONOSPACED", PLAIN, 13));
            labelsTarj4[i].setForeground(Color.BLACK);
            labelsTarj4[i].setBounds(coorx3,coory8,width1,20);
            tarjetas[3].add(labelsTarj4[i]);
            coory8 += 195;
            width1 += 50;
            coorx3 += 60;
        }

        labelsTarj4[1].setVisible(false);

        //Creamos el checkbox para saber si guardar los datos en un fichero o no
        checkBox = new JCheckBox("", false);
        checkBox.setBounds(425,30,30,20);
        checkBox.setBackground(Color.LIGHT_GRAY);
        checkBox.setRolloverEnabled(false);
        tarjetas[3].add(checkBox);

        //Creamos el JTextPane donde se visualizarán todos los datos recopilados en las otras dos tarjetas
        panelTarj4 = new JTextPane();
        panelTarj4.setBounds(115,60,400,140);
        panelTarj4.setBackground(Color.LIGHT_GRAY);
        panelTarj4.setEditable(false);
        panelTarj4.setFont(new Font("MONOSPACED", PLAIN, 16));
        panelTarj4.setForeground(Color.BLACK);
        tarjetas[3].add(panelTarj4);
        panelTarj4.setVisible(false);

        //Creamos un botón que guardará los datos en un fichero
        botonChooser = new JButton("Guardar");
        botonChooser.setBounds(400, 255, 70, 20);
        botonChooser.setFont(new Font("MONOSPACED", PLAIN, 13));
        botonChooser.setOpaque(true);
        botonChooser.setFocusPainted(false);
        botonChooser.setBackground(Color.LIGHT_GRAY);
        botonChooser.setForeground(Color.BLACK);
        botonChooser.setBorder(new LineBorder(Color.BLACK));
        tarjetas[3].add(botonChooser);
        botonChooser.setVisible(false);

        //Creamos el textField para introducir el nombre del fichero
        nombreFichero = new JTextField();
        nombreFichero.setBackground(Color.LIGHT_GRAY);
        nombreFichero.setFont(new Font("MONOSPACED", PLAIN, 13));
        nombreFichero.setForeground(Color.BLACK);
        nombreFichero.setBorder(new LineBorder(Color.BLACK));
        nombreFichero.setBounds(115,255,280,20);
        tarjetas[3].add(nombreFichero);
        nombreFichero.setVisible(false);

        //Creamos un label que se visualizará cuando no se aporte una ruta para el fichero o
        //haya ocurrido un error guardando el fichero
        labelErrorFichero = new JLabel("No se ha aportado una ruta o hubo un error guardando el fichero.");
        labelErrorFichero.setBackground(Color.LIGHT_GRAY);
        labelErrorFichero.setFont(new Font("MONOSPACED", PLAIN, 13));
        labelErrorFichero.setForeground(Color.RED);
        labelErrorFichero.setBounds(37,300,525,20);
        tarjetas[3].add(labelErrorFichero);
        labelErrorFichero.setVisible(false);

        botonChooser.addActionListener(new ActionListener() {
            //Un action listener del botón que llamará a la función para guardar los datos en un fichero
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarArchivo();
            }
        });

        checkBox.addChangeListener(new ChangeListener() {
            //Creamos lel change listener del checkBox para saber si guardar el fichero
            @Override
            public void stateChanged(ChangeEvent e) {
                if(checkBox.isSelected()){
                    //Si es pulsado que recopile todos los datos de el resto de tarjetas y lo muestre en el JTextPane,
                    //además que muestre la opción para que se guarde el fichero
                    guardarCheckBox = true;
                    nombreSeleccionado = nombreEmail[0].getText();
                    emailSeleccionado = nombreEmail[1].getText();
                    contrasenaSeleccionada = contrasena[0].getText();
                    paisSeleccionado = (String) comboBox[0].getSelectedItem();
                    regionSeleccionada = (String) comboBox[1].getSelectedItem();

                    String textoFinal = "Datos guardados:\nNombre de usuario: " +
                            nombreSeleccionado + ".\nEmail: " + emailSeleccionado + ".\n" +
                            "Contraseña: " + contrasenaSeleccionada + ".\nVive en: " + regionSeleccionada + ", " +
                            paisSeleccionado + ".";

                    panelTarj4.setText(textoFinal);

                    panelTarj4.setVisible(true);
                    labelsTarj4[1].setVisible(true);
                    botonChooser.setVisible(true);
                    nombreFichero.setVisible(true);
                }
                else{
                    //Si no es pulsado, que oculte todo
                    guardarCheckBox = false;
                    panelTarj4.setVisible(false);
                    labelsTarj4[1].setVisible(false);
                    botonChooser.setVisible(false);
                    nombreFichero.setVisible(false);
                }
            }
        });
    }

    private void guardarArchivo() {
        //Creamos el JFileChooser y le asignamos que guarde la información del JTextPane de la cuarta tarjeta,
        //en un fichero en la ruta que desee el usuario
        try{
            fileChooser= new JFileChooser();
            fileChooser.showSaveDialog(this);
            File guardar = fileChooser.getSelectedFile();

            if(guardar != null) {
                FileWriter save =new FileWriter(nombreFichero.getText()+".txt");
                save.write(panelTarj4.getText());
                save.close();
                guardarExitoso = true;
            }
        }
        catch(IOException ex){
            guardarExitoso = false;
        }
    }

    public void initTarjeta5(){
        //Creamos un JTextPane con un texto que informe al usuario que su información ha sido guardado correctamente
        panelTarj5 = new JTextPane();
        panelTarj5.setBounds(100,30,400,300);
        panelTarj5.setBackground(Color.LIGHT_GRAY);
        panelTarj5.setEditable(false);
        panelTarj5.setFont(new Font("MONOSPACED", PLAIN, 16));
        panelTarj5.setForeground(Color.BLACK);
        tarjetas[4].add(panelTarj5);

        String texto = "Sus datos se han guardado correctamente.\nYa puede disfrutar de la aplicación." +
                "\n¡Gracias por confiar en Smart Cube!";
        panelTarj5.setText(texto);
    }

    public void initBotones(){
        for (int i = 0; i < 2; i++) {
            //Creamos los dos botones de siguiente y anterior tarjeta
            botonesTarjetas[i] = new JButton(botTarjTitulos[i]);
            botonesTarjetas[i].setBounds(coorx1, 20, 150, 35);
            botonesTarjetas[i].setFont(new Font("MONOSPACED", PLAIN, 20));
            botonesTarjetas[i].setOpaque(true);
            botonesTarjetas[i].setFocusPainted(false);
            botonesTarjetas[i].setBackground(Color.BLACK);
            botonesTarjetas[i].setForeground(Color.LIGHT_GRAY);
            botonesTarjetas[i].setBorder(new LineBorder(Color.BLACK));
            panelInferior.add(botonesTarjetas[i]);
            coorx1 += 375;
        }

        //Creamos el botón de finalizar que solo será visible en la última tarjeta
        botonFinalizar = new JButton("Finalizar");
        botonFinalizar.setBounds(405, 20, 150, 35);
        botonFinalizar.setFont(new Font("MONOSPACED", PLAIN, 20));
        botonFinalizar.setOpaque(true);
        botonFinalizar.setFocusPainted(false);
        botonFinalizar.setBackground(Color.BLACK);
        botonFinalizar.setForeground(Color.LIGHT_GRAY);
        botonFinalizar.setBorder(new LineBorder(Color.BLACK));
        panelInferior.add(botonFinalizar);
        botonFinalizar.setVisible(false);

        //Llamamos a la función initFunciones donde se almacena el funcionamiento de los tres botones
        initFunciones();
    }

    public void initFunciones(){
        botonesTarjetas[0].setVisible(false);
        for (int i = 0; i < 2; i++) {
            botonesTarjetas[i].addActionListener(new ActionListener(){
                //Creamos el actionListener del botón de anterior y siguiente
                @Override
                public void actionPerformed(ActionEvent e){
                    if(e.getSource() == botonesTarjetas[0]){
                        //Si el botón anterior es pulsado llevará a la tarjeta anterior, sin embargo,
                        //dependiendo de en que tarjeta nos encontremos se visualizará el botón de siguiente,
                        //finalizar o el propio botón de atrás
                        if(tarjetas[1].isShowing()){layoutTarjetas.previous(panelTarjetas);
                            botonesTarjetas[0].setVisible(false);}
                        else if(tarjetas[2].isShowing()){layoutTarjetas.previous(panelTarjetas);}
                        else if(tarjetas[3].isShowing()){layoutTarjetas.previous(panelTarjetas);}
                        else if(tarjetas[4].isShowing()){layoutTarjetas.previous(panelTarjetas);
                            botonesTarjetas[1].setVisible(true);
                            botonFinalizar.setVisible(false);
                        }
                    }
                    if(e.getSource() == botonesTarjetas[1]){
                        //Si el botón siguiente es pulsado llevará a la siguiente tarjeta.
                        //Dependiendo de en que tarjeta nos encontremos se visualizará el botón de atrás,
                        //finalizar o el propio botón de siguiente. Por último,
                        //comprobará si se cumplen las condiciones en las tarjetas 2, 3 y 4 para poder continuar

                        if(tarjetas[0].isShowing()){layoutTarjetas.next(panelTarjetas);
                            botonesTarjetas[0].setVisible(true);}
                        else if(tarjetas[1].isShowing()){
                            //Si la segunda tarjeta se está mostrando, las condiciones que sí se cumplan
                            //se marcarán en negro.
                            for (int i = 0; i < 6; i++){
                                condicionesContrasena[i].setForeground(Color.BLACK);
                            }
                            for (int i = 0; i < 3; i++){
                                condicionesEmail[i].setForeground(Color.BLACK);
                            }

                            boolean siguiente = true;
                            String nombre = nombreEmail[0].getText();
                            String email = nombreEmail[1].getText();
                            String email1[] = email.split("");
                            int contEmail = 0;

                            //Que compruebe si en los dos TextField de email y los dos PasswordField
                            //se han introducido los datos. Después que ponga en rojo los labels de las condiciones
                            //que no se cumplan.
                            if(nombre.length()>0 && email.length()>0){
                                labelNombreEmail.setVisible(false);
                                for (int i = 0; i < email1.length; i++) {
                                    if(email1[i].contains("@")){
                                        contEmail += 1;
                                    }
                                }
                                if(contEmail != 1){
                                    siguiente = false;
                                    condicionesEmail[0].setForeground(Color.RED);
                                }
                                if(!email1[0].toUpperCase().matches("[A-Z]*")){
                                    siguiente = false;
                                    condicionesEmail[1].setForeground(Color.RED);
                                }
                                if (!email.contains("@gmail.com") && !email.contains("@hotmail.com") &&
                                        !email.contains("@outlook.com") && !email.contains("@yahoo.com")) {
                                    siguiente = false;
                                    condicionesEmail[2].setForeground(Color.RED);
                                }
                            }
                            else{
                                for (int i = 0; i < 3; i++){
                                    condicionesEmail[i].setForeground(Color.RED);
                                }
                                labelNombreEmail.setVisible(true);
                                siguiente = false;
                            }

                            String contrasena1 = contrasena[0].getText();
                            String contrasena1b = contrasena[1].getText();
                            String contrasena2[] = contrasena1.split("");
                            String contrasena2b[] = contrasena1b.split("");
                            boolean digito = false, minuscula = false, mayuscula = false, caracter = false,
                                    longitud = false;
                            int misma = 0;

                            if(contrasena1.length()>0 && contrasena1b.length()>0){
                                labelContrasena.setVisible(false);
                                if(contrasena1.length()<8 || contrasena1.length()>16){
                                    condicionesContrasena[0].setForeground(Color.RED);
                                    siguiente = false;
                                }
                                if(contrasena1.length()==contrasena1b.length()){
                                    longitud = true;
                                }
                                for (int j = 0; j < contrasena2.length; j++) {
                                    if(!contrasena2[j].toUpperCase().matches("[A-Z]*") &&
                                            !contrasena2[j].matches("[0-9]*")){
                                        caracter = true;
                                    }
                                    else{
                                        if(contrasena2[j].matches("[0-9]*")){
                                            digito = true;
                                        }
                                        else{
                                            if(contrasena2[j].equals(contrasena2[j].toLowerCase())){
                                                minuscula = true;
                                            }
                                            if(contrasena2[j].equals(contrasena2[j].toUpperCase())){
                                                mayuscula = true;
                                            }
                                        }
                                    }
                                }
                                if(longitud==true){
                                    for (int j = 0; j < contrasena2.length; j++) {
                                        if(contrasena2b[j].equals(contrasena2[j])){
                                            misma += 1;
                                        }
                                    }
                                }

                                if(digito==false){siguiente = false; condicionesContrasena[1].setForeground(Color.RED);}
                                if(mayuscula==false){siguiente = false; condicionesContrasena[2].setForeground(Color.RED);}
                                if(minuscula==false){siguiente = false; condicionesContrasena[3].setForeground(Color.RED);}
                                if(caracter==false){siguiente = false; condicionesContrasena[4].setForeground(Color.RED);}
                                if(misma != contrasena1.length() || longitud==false){
                                    siguiente = false;
                                    condicionesContrasena[5].setForeground(Color.RED);
                                }
                            }
                            else{
                                for (int i = 0; i < 6; i++){
                                    condicionesContrasena[i].setForeground(Color.RED);
                                }
                                labelContrasena.setVisible(true);
                                siguiente = false;
                            }

                            if(siguiente==true){
                                //Cuando todas las condiciones se cumplan, que se le permita al usuario avanzar
                                botonesTarjetas[1].setBackground(Color.BLACK);
                                layoutTarjetas.next(panelTarjetas);
                            }
                            else if(siguiente==false){
                                //Si se incumple al menos una condición que inhabilite el poder seguir avanzando
                                //con el botón de siguiente.
                                botonesTarjetas[1].setBackground(Color.RED);
                            }
                        }
                        else if(tarjetas[2].isShowing()){
                            //Si la tercera tarjeta se está mostrando
                            //que compruebe que se haya escogido un item válido de los dos comboBox
                            if (comboBox[0].getSelectedIndex()==0 || comboBox[1].getSelectedIndex()==0){
                                //Si al emnos un item es inválido, que no se le permita al usuario avanzar
                                //y se muestre un label de error
                                labelPais.setVisible(true);
                                botonesTarjetas[1].setBackground(Color.RED);
                            }
                            else {
                                //Si ambos item son válidos, que se le permita al usuario avanzar
                                labelPais.setVisible(false);
                                botonesTarjetas[1].setBackground(Color.BLACK);
                                layoutTarjetas.next(panelTarjetas);
                            }
                        }
                        else if(tarjetas[3].isShowing()){
                            //Si la cuarta tarjeta se está mostrando
                            //que compruebe si ha habido alguna irregularidad guardando el fichero con los datos
                            if(guardarCheckBox == false || guardarExitoso == true){
                                labelErrorFichero.setVisible(false);
                                botonesTarjetas[1].setVisible(false);
                                botonFinalizar.setVisible(true);
                                botonesTarjetas[1].setBackground(Color.BLACK);
                                layoutTarjetas.next(panelTarjetas);
                            }
                            else if(guardarExitoso == false){
                                labelErrorFichero.setVisible(true);
                                botonesTarjetas[1].setBackground(Color.RED);
                            }
                        }
                    }
                }
            });
        }

        botonFinalizar.addActionListener(new ActionListener() {
            //Un actionListener del botón finalizar que si se pulsa se cierre la aplicación
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(WIDTH);
            }
        });
    }

    @Override
    public void paint(Graphics g){
        //Llamamos a la función graphics para que en la parte superior de la aplicación,
        //se muestre un degradado y una imagen como logo
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        GradientPaint gp = new GradientPaint(0, 0, Color.BLACK, 600, 105, Color.LIGHT_GRAY);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, 600, 105);

        Toolkit t = Toolkit.getDefaultToolkit();
        Image imagen = t.getImage ("C:\\Users\\ivanb\\OneDrive\\Imágenes\\logo.png");
        g2d.drawImage(imagen, 262, 30, this);

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setFont(new Font("MONOSPACED", PLAIN, 30));
        g2d.drawString("Smart Cube", 30, 80);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("MONOSPACED", PLAIN, 30));
        g2d.drawString("Just Think", 380, 80);
    }

    public static void main(String[] args) throws IOException {new Practica2IvanGarcia();}
}
