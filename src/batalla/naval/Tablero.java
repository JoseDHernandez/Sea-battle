package batalla.naval;

import Classes.Player;
import Classes.getImage;
import Classes.getRandom;
import Entitys.Boat;
import Entitys.Locator;
import Entitys.Mine;
import Entitys.Power;
import Entitys.Submarine;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Jose
 */
public class Tablero extends javax.swing.JPanel {

    //Dificultad
    private int DIFFICULTY; // 0 = Normal, 1 = Medio, 2=Dificil
    // Tamaño del tablero y espaciado entre celdas
    private int Size;
    private final int Gap = 1;
    private final int ButtonSize = 50;
    private char LAST_LETTER; // Última letra posible en el tablero
    private getImage img = new getImage(); //Clase para oobtener los iconos
    private getRandom random = new getRandom();
    private Color BOARD_BG_COLOR = Color.WHITE;
    // Indicadores de jugador y estado de construcción
    private boolean isPlayer; // Si es el jugador o enemigo
    private boolean inBuild; // Indica si se está construyendo el tablero

    // Jugadores y elementos del juego
    private Player player; // Jugador actual
    private Player enemy; // Jugador enemigo
    private Boat actualBoat; // Barco actualmente seleccionado
    private Power actualPowerUp; // PowerUp actualmente seleccionado
    private List<String> priorityCellsEnemy = new ArrayList<>(); //Lista de celdas prioritarias del enemigo
    // Lista de botones/elementos gráficos
    private List<JButton> buttons; // Lista de botones

    // Listas de bordes para validación de celdas
    private List<String> borderRight = new ArrayList<>();
    private List<String> borderLeft = new ArrayList<>();
    private List<String> borderTop = new ArrayList<>();
    private List<String> borderButton = new ArrayList<>();

    // Icono para celdas vacías
    private final ImageIcon VOID_CELL = new ImageIcon(System.getProperty("user.dir") + "\\src\\img\\Z.png");

    // Lista de celdas "fantasma" pintadas
    private List<String> ghostCells = new ArrayList<>();

    // Programación de tareas periódicas
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * Lista de entidades a usar (barcos y minas) Nota: Las minas deben ir de
     * ultimas: 1 - 3 = Barcos, 0 = Mina
     */
    private List<Integer> entityList = new ArrayList<>();
    private List<Integer> copyOfEntityList = new ArrayList<>();//Lista para copia de la lista de entidades
    //Lista de celdas

    private List<String> OriginalListCells = new ArrayList<>();

    /**
     * Constructor de Tablero
     *
     * @param Size Tamaño del tablero
     * @param Difficulty Numero de la dificultad (0 = Normal, 1 = Medio,
     * 2=Dificil)
     * @param EntitysList Lista de las entidades
     */
    public Tablero(int Size, int Difficulty, List<Integer> EntitysList) {
        // Inicialización de variables
        this.DIFFICULTY = Difficulty;
        this.Size = Size; // Tamaño del tablero
        this.LAST_LETTER = (char) ('A' + (Size - 1)); // Última letra en el tablero según el tamaño
        this.isPlayer = true; // Indica si es el turno del jugador
        this.inBuild = true; // Indica si se está construyendo el tablero
        this.buttons = new ArrayList<>(); // Lista de botones y elementos gráficos del tablero
        this.random = new getRandom(Size);
        // Inicialización de jugadores y elementos del juego
        this.player = new Player(); // Jugador actual
        player.setTypeAttack(true); // Tipo de ataque del jugador
        this.actualBoat = new Boat(); // Barco actualmente seleccionado
        this.enemy = new Player("Bot"); // Jugador enemigo
        this.actualPowerUp = null; // PowerUp actualmente seleccionado
        this.entityList = EntitysList; //Entidades disponibles
        this.img = new getImage(player);//Clase getImage
        // Inicialización de componentes gráficos y de juego
        initComponents(); // Inicializa los componentes gráficos del tablero
        initCells(); // Inicializa las celdas del tablero
        PanelGame.setVisible(false);
        setBorders(); // Define los bordes del tablero para validación de celdas
        generateCoordsOfBoats(enemy);//Crear tablero del enemigo IA
        changeEntity();//Inicializa la lista de entidades y construccion
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        Panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ImageBoat = new javax.swing.JLabel();
        L_Healtd = new javax.swing.JLabel();
        L_Size = new javax.swing.JLabel();
        L_Orientation = new javax.swing.JLabel();
        rotateButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        PlayButton = new javax.swing.JButton();
        TablePanel = new javax.swing.JPanel();
        PanelGame = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        rotateButton1 = new javax.swing.JButton();
        Board = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(255, 255, 153));
        setMinimumSize(getDimension());

        Panel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Barco actual:");

        ImageBoat.setIcon(new ImageIcon(getImage.urlOfImage("0")));
        ImageBoat.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        ImageBoat.setIconTextGap(0);
        ImageBoat.setMaximumSize(new java.awt.Dimension(150, 150));
        ImageBoat.setMinimumSize(new java.awt.Dimension(150, 150));
        ImageBoat.setPreferredSize(new java.awt.Dimension(150, 150));

        L_Healtd.setText("Vida:");

        L_Size.setText("Tamaño:");

        L_Orientation.setText("Orientación:");

        rotateButton.setText("Rotar");
        rotateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rotateButtonMouseClicked(evt);
            }
        });
        rotateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotateButtonActionPerformed(evt);
            }
        });

        jButton2.setText("Posiciones aleatorias");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setText("Limpiar tablero");
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton3KeyPressed(evt);
            }
        });

        jLabel2.setText("Herramientas:");

        PlayButton.setText("Atacar");
        PlayButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PlayButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(L_Healtd)
                            .addComponent(L_Size)
                            .addComponent(L_Orientation)))
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(rotateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel2))
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(PanelLayout.createSequentialGroup()
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(PlayButton))
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(ImageBoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 50, Short.MAX_VALUE))
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(ImageBoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(L_Healtd)
                .addGap(18, 18, 18)
                .addComponent(L_Size)
                .addGap(18, 18, 18)
                .addComponent(L_Orientation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rotateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(PlayButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TablePanel.setBackground(new java.awt.Color(204, 255, 255));
        TablePanel.setPreferredSize(new java.awt.Dimension(642, 588));
        TablePanel.setLayout(new java.awt.GridLayout(1, 0));

        PanelGame.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Ataques especiales");

        rotateButton1.setText("Rotar");
        rotateButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rotateButton1MouseClicked(evt);
            }
        });
        rotateButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotateButton1ActionPerformed(evt);
            }
        });

        Board.setBackground(new java.awt.Color(102, 102, 102));
        Board.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Tu tablero");

        javax.swing.GroupLayout PanelGameLayout = new javax.swing.GroupLayout(PanelGame);
        PanelGame.setLayout(PanelGameLayout);
        PanelGameLayout.setHorizontalGroup(
            PanelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelGameLayout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(rotateButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
            .addGroup(PanelGameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelGameLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelGameLayout.createSequentialGroup()
                        .addGroup(PanelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Board, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(PanelGameLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        PanelGameLayout.setVerticalGroup(
            PanelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rotateButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Board, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PanelGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Funcion de animacion y verificacion de impacto de los torpedos de un
     * submarino
     *
     * @param submarine Objeto submarino (actualPower)
     * @param codeCoords Coordenadas de la celda central (Celda seleccionada)
     * @param coords Lista de celdas en coordenas del submarino
     */
    private void submarineTorpedoes(Submarine submarine, String codeCoords, List<String> coords) {

        ScheduledFuture<?> task = scheduler.scheduleAtFixedRate(() -> {

            //Array de las cordenas de los torpedos
            String[] shootsCoords = (submarine.getTorpedoes().isEmpty())
                    ? new String[]{submarine.getPosition(), submarine.getPosition()}
                    : submarine.getTorpedoes().toArray(new String[0]);

            //Siente coordenada del torpedo
            String[] afterShoot = (submarine.getTorpedoes().isEmpty())
                    ? submarine.shoot(player.getCells()) : submarine.shoot(shootsCoords, enemy.getCells());

            // Código de la tarea a ejecutar
            boolean shoot0 = true;
            boolean shoot1 = true;
            //Verificar si se pasa de los limites el afterShoot
            if (!submarine.getOrientation()) {
                //1-10
                int af0 = Integer.parseInt(afterShoot[0].substring(1));
                int af1 = Integer.parseInt(afterShoot[1].substring(1));
                if (af0 < 1 || af0 > Size) {
                    shoot0 = false;
                }
                if (af1 < 1 || af1 > Size) {
                    shoot1 = false;
                }
            } else {
                //A-J
                char af0 = afterShoot[0].substring(0, 1).charAt(0);
                char af1 = afterShoot[1].substring(0, 1).charAt(0);
                if (!(af0 >= 'A' && af0 <= LAST_LETTER)) {
                    shoot0 = false;
                }
                if (!(af1 >= 'A' && af1 <= LAST_LETTER)) {
                    shoot1 = false;
                }
            }
            //se ejecuta el analizis de impacto
            //TODO: Verificar impacto con entidades

            if (shoot0 || shoot1) {

                //Posion anterior de torpedos
                if (!shootsCoords[0].equals(codeCoords)) {
                    submarine.setTorpedoes(shootsCoords);

                }
                //Validar si hay algun impacto en alguno de los lados
                if (submarine.verifyImpact(Arrays.asList(afterShoot), coords)) {
                    if (shoot0 && submarine.verifyImpact(afterShoot[0], coords)) {

                        shootsCoords[1] = afterShoot[1];
                        shoot0 = false;

                    }
                    if (shoot1 && submarine.verifyImpact(afterShoot[1], coords)) {
                        shootsCoords[0] = afterShoot[0];

                        shoot1 = false;
                    }
                } else {
                    //Nuevas coordenas
                    shootsCoords = afterShoot;
                }

                // shootsCoords a list
                List<String> shootList = Arrays.asList(shootsCoords);
                afterShoot = submarine.shoot(shootList);
                //Registrar coordenadas
                submarine.addCell(shootList);
                submarine.setTorpedoes(shootsCoords);
                //Cambiar icono por torpedo
                paintCells(true, shootList);
                player.addCell(shootList);
                //Cmabiar torpedo por impacto
                if (shoot0 == false) {
                    System.out.println("Impacto en 0");
                }
                if (shoot1 == false) {
                    System.out.println("Impacto en 1");
                }
            } else {
                System.out.println("Fin de los torpedos ");
                scheduler.shutdown();
                actualPowerUp = new Power();
                player.setTypeAttack(true);
            }

        }, 0, 1, TimeUnit.SECONDS);

    }

    /**
     * Maneja el evento de clic de una celda del tablero.
     *
     * @param Cell El botón de celda en el que se hizo clic.
     */
    private void PositionClick(JButton Cell) {

        String codeCoords = Cell.getName();
        List<String> coords = actualBoat != null && actualBoat.getSize() > 1 ? coordToCells(codeCoords) : Arrays.asList(codeCoords);
        if (validateCells(coords, player) && validateCellPosition(codeCoords)) {
            //Entidades de una celda
            if (actualPowerUp instanceof Mine) {
                player.addPower(actualPowerUp);
            }
            if (actualBoat != null) {
                actualBoat.setCoords(coords);
                player.addBoat(actualBoat);
            }
            player.addCell(coords);
            paintCells(true, coords);
            if (!inBuild) {
                if (actualPowerUp == null && player.getTypeAttack()) {
                    int idBoat = impactVerification(codeCoords, player, enemy);
                    drawImpact(idBoat, codeCoords, player);
                    //Cambio de turno
                    turnChange();
                } //================PowerUp====================
                else if (actualPowerUp instanceof Submarine) {
                    Submarine submarine = (Submarine) actualPowerUp;
                    //Establer posicion para atacar
                    submarine.setPosition(codeCoords);
                    //Verificar si el propio submario impacto algo
                    if (submarine.verifyImpact(coords, enemy.getCells())) {
                        //TODO: Verificar impacto del submarina en las tre coordenadas de su ubicacion (validar si hay una mina)
                    }
                    submarine.addCell(codeCoords);

                    //Ciclo de animacion y ejecucion de los torpedos
                    submarineTorpedoes(submarine, codeCoords, coords);

                    //TODO:Vaciar actualPowerUp
                } else if (actualPowerUp instanceof Locator) {
                    //TODO:Logica de locator
                }
            } else {
                //Cambiar de entidad
                changeEntity();
            }
        }

    }

    /**
     * Estable las coordenadas de los bordes en sus respectivas listas
     */
    private void setBorders() {
        for (int i = 1; i <= Size; i++) {
            borderRight.add("A" + i);
            borderLeft.add(String.valueOf(LAST_LETTER) + i);
        }

        for (char letra = 'A'; letra <= LAST_LETTER; letra++) {
            borderTop.add(letra + "1");
            borderButton.add(letra + String.valueOf(Size));
        }
    }

    /**
     * Obtiene el botón de celda correspondiente al nombre especificado.
     *
     * @param name El nombre (Coordenada) del botón de celda que se desea
     * obtener.
     * @return El botón de celda correspondiente al nombre especificado.
     * @throws IllegalArgumentException si no se encuentra ningún botón con el
     * nombre especificado.
     */
    private JButton getCell(String name) {
        for (JButton bt : buttons) {
            if (bt.getName().equals(name)) {
                return bt;
            }
        }
        return new JButton();
    }

    private JPanel getCellBoard(String name) {
        Component[] cells = Board.getComponents();
        for (Component button : cells) {
            JPanel cell = (JPanel) button;
            if (cell.getName().equals("B_" + name)) {
                return cell;
            }
        }
        return new JPanel();
    }

    /**
     * Convierte una coordenada en una lista de coordenadas cercanas, según las
     * reglas del juego para una entidad.
     *
     * @param coord La coordenada a convertir.
     * @return Una lista de coordenadas ocupadas por la entidad.
     */
    private List<String> coordToCells(String coord) {
        List<String> coords = new ArrayList<>();
        int num = Integer.parseInt(coord.substring(1));
        //Coordenas cercanas
        String coordLeft = ((char) (((int) coord.charAt(0)) - 1)) + "" + num;
        String coordRight = ((char) (((int) coord.charAt(0)) + 1)) + "" + num;
        String coordTop = coord.charAt(0) + "" + (num - 1);
        String coordButton = coord.charAt(0) + "" + (num + 1);
        if (inBuild) {
            if (player.getTypeAttack() && actualBoat != null) {
                int size = actualBoat.getSize();
                boolean orientation = actualBoat.getOrientation();
                //vert

                if (orientation) {
                    //horizo
                    if (size == 3) {
                        coords.add(coordTop);
                        coords.add(coord);
                        coords.add(coordButton);
                    } else {
                        coords.add(coord);
                        coords.add(coordButton);
                    }

                } else {
                    if (size == 3) {
                        coords.add(coordLeft);
                        coords.add(coord);
                        coords.add(coordRight);
                    } else {
                        coords.add(coord);
                        coords.add(coordRight);
                    }
                }
            } else {
                //Mine
                coords.add(coord);
            }
        } else if (player.getTypeAttack() == false) {
            if (actualPowerUp instanceof Locator) {
                /*
                0 1 2
                3 [4] 5
                6 7 8
                 */
                String coordLeftTop = ((char) (((int) coordTop.charAt(0)) - 1)) + "" + (num - 1);
                String coordRightTop = ((char) (((int) coordTop.charAt(0)) + 1)) + "" + (num - 1);
                String coordLeftButton = ((char) (((int) coordButton.charAt(0)) - 1)) + "" + (num + 1);
                String coordRightButton = ((char) (((int) coordButton.charAt(0)) + 1)) + "" + (num + 1);
                //asginar coordenadas
                //Arriba
                coords.add(coordLeftTop);
                coords.add(coordTop);
                coords.add(coordRightTop);
                //centro
                coords.add(coordLeft);
                coords.add(coord);
                coords.add(coordRight);
                //Abajo
                coords.add(coordLeftButton);
                coords.add(coordButton);
                coords.add(coordRightButton);

            } else if (actualPowerUp instanceof Submarine) {
                //Submarine
                if (((Submarine) actualPowerUp).getOrientation()) {
                    coords.add(coordTop);
                    coords.add(coord);
                    coords.add(coordButton);
                } else {
                    coords.add(coordLeft);
                    coords.add(coord);
                    coords.add(coordRight);
                }
            }
        }
        return coords;
    }

    /**
     * Borra las celdas fantasma del tablero, restaurando su apariencia original
     * a {@code VOID_CELL}.
     */
    private void clearGhostCells() {

        for (String cell : ghostCells) {
            getCell(cell).setIcon(VOID_CELL);
        }
        ghostCells = new ArrayList<>();
    }

    /**
     * Este método se encarga de pintar las celdas del tablero según diferentes
     * condiciones, como el tipo de estado (ataque o jugada), la presencia de un
     * PowerUp activo y el tipo de PowerUp.
     *
     * @param type indica si se está pintando una celda real (true) o una celda
     * "fantasma" para previsualización (false).
     * @param cells lista de coordenadas de las celdas a pintar.
     */
    private void paintCells(boolean type, List<String> cells) {
        //Limpiar celdas
        clearGhostCells();
        //Pintar nuevas celda
        List<ImageIcon> listImages = img.getListIcons(type, cells, actualBoat, actualPowerUp, inBuild);
        if (!listImages.isEmpty()) {
            for (int i = 0; i < listImages.size(); i++) {
                getCell(cells.get(i)).setIcon(listImages.get(i));
            }
        }
        //Cambiar cells a ghost cells
        if (!type) {
            ghostCells = cells;
        }
    }

    /**
     * Este método se encarga de pintar las celdas del tablero como celdas
     * "fantasma" para previsualización.
     *
     * @param cells lista de coordenadas de las celdas a pintar.
     */
    private void paintCells(List<String> cells) {
        paintCells(false, cells);
    }

    /**
     * Metodo para limpiar visualmente todas las celdas
     */
    private void clearAllCells() {
        for (JButton button : buttons) {
            if (player.getCells().contains(button.getName())) {
                button.setIcon(VOID_CELL);
            }
        }
    }

    /**
     * Este método se encarga de pintar una celda en el tablero.
     *
     * @param cell La coordenada de la celda a pintar.
     */
    private void paintCells(boolean type, String cell) {
        paintCells(type, Arrays.asList(cell));
    }

    /**
     * Valida si las celdas especificadas están disponibles para colocar un
     * barco. Comprueba si alguna de las celdas especificadas ya está ocupada
     * por otro barco.
     *
     * @param cells La lista de celdas a validar.
     * @return {@code true} si todas las celdas están disponibles, {@code false}
     * si al menos una celda está ocupada.
     */
    private boolean validateCells(List<String> cells, Player p) {
        for (String cell : cells) {
            if (p.getCells().contains(cell)) {
                return false;
            }
        }
        return true;
    }

    private boolean validateCellPosition(String cellName) {
        return validateCellPosition(cellName, player);
    }

    /**
     * Valida si la posición de la celda especificada es válida para colocar una
     * entidad. Comprueba si la celda está dentro de los límites del tablero y
     * si es una posición válida para la orientación y tamaño de la entidad.
     *
     * @param cellName El nombre de la celda a validar.
     * @return {@code true} si la posición de la celda es válida, {@code false}
     * si la posición está fuera de los límites o no es adecuada para la entidad
     * actual.
     */
    private boolean validateCellPosition(String cellName, Player player) {
        boolean temp = false;
        if (inBuild && player.getTypeAttack() && actualBoat != null) {
            int longBoat = actualBoat.getSize();
            //false = horizontal, true = vertical
            boolean orientation = actualBoat.getOrientation();
            if (longBoat > 1) {
                if (orientation) {
                    //Botes horizontales
                    if (!borderButton.contains(cellName)) {
                        //Barcos de 3
                        if (longBoat == 3 && !borderTop.contains(cellName)) {
                            temp = true;
                        } else if (longBoat == 2) {
                            //Barcos de 2
                            temp = true;
                        }
                    }

                } else {
                    if (!borderRight.contains(cellName)) {
                        //Barcos de 3
                        if (longBoat == 3 && !borderLeft.contains(cellName)) {
                            temp = true;
                        } else if (longBoat == 2) {
                            //Barcos de 2
                            temp = true;
                        }
                    }
                }
            } else {
                temp = true;
            }
        } else if (player.getTypeAttack() == false && inBuild) {
            //Mine
            temp = true;
        } else {
            if (!inBuild) {
                temp = true;
            } //Locator y submarine
            else if (actualPowerUp instanceof Locator) {
                /*
                0 1 2
                3 [4] 5
                6 7 8
                 */

                if (!borderRight.contains(cellName) && !borderTop.contains(cellName) && !borderLeft.contains(cellName) && !borderButton.contains(cellName)) {
                    temp = true;
                }
            } else if (actualPowerUp instanceof Submarine) {
                if (((Submarine) actualPowerUp).getOrientation()) {

                    if (!borderButton.contains(cellName) && !borderTop.contains(cellName)) {
                        temp = true;

                    }

                } else {
                    if (!borderRight.contains(cellName) && !borderLeft.contains(cellName)) {
                        temp = true;

                    }
                }
            }
        }
        return temp;
    }

    /**
     * Cambia el turno entre el jugador y la computadora.
     *
     * Este método cambia el turno entre el jugador y la computadora en el
     * juego. Actualiza la variable de control `isPlayer` para reflejar quién
     * tiene el turno actual. Además, actualiza el tipo de ataque del jugador
     * actual, estableciéndolo como `true` si es el turno del jugador o como
     * `false` si es el turno de la computadora.
     *
     * Después de cambiar el turno, si el turno actual es de la computadora, se
     * realiza el ataque del enemigo llamando al método `enemyActtack()`.
     */
    private void turnChange() {
        isPlayer = !isPlayer;
        player.setTypeAttack(true);
        System.out.println("Player: " + player.getCells().size());
        System.out.println("Bot: " + enemy.getCells().size());
        if (isPlayer) {
            //Jugador
        } else {
            //PC
            enemyActtack();
        }
    }

    /**
     * Muestra en el panel de información los datos del barco y una imagen de
     * este rotada segun el barco actual
     */
    private void displayBoatInformation() {
        if (actualBoat != null) {
            int index = 11;
            switch (actualBoat.getSize()) {
                case 1 ->
                    index = 12;
                case 2 ->
                    index = 13;
                case 3 ->
                    index = 14;
            }
            ImageBoat.setIcon(img.getIcon(true, index, actualBoat, actualPowerUp, inBuild));
            L_Healtd.setText("Vida: " + actualBoat.getLife());
            String orient;
            orient = switch (actualBoat.getRotation()) {
                case 1 ->
                    "Norte";
                case 2 ->
                    "Este";
                case 3 ->
                    "Sur";
                default ->
                    "Oeste";
            };
            L_Orientation.setText("Orientación: " + orient);
            L_Size.setText("Tamaño: " + actualBoat.getSize());
        } else {
            ImageBoat.setIcon(new ImageIcon(getImage.urlOfImage("0")));
            L_Healtd.setText("Vida: ");
            L_Orientation.setText("Orientación: ");
            L_Size.setText("Tamaño: ");
        }
    }

    private void clearEnetitysOfPlayer(Player tempPlayer) {
        clearAllCells();
        tempPlayer.clearCells();
        tempPlayer.clearBoatsList();
        tempPlayer.setListCells(OriginalListCells);
    }

    /**
     * Genera las coordenadas de los barcos para el jugador especificado. Borra
     * las celdas y la lista de barcos del jugador antes de generar las nuevas
     * coordenadas.
     *
     * @param tempPlayer El jugador para el cual generar las coordenadas de los
     * barcos.
     */
    private void generateCoordsOfBoats(Player tempPlayer) {
        clearEnetitysOfPlayer(tempPlayer);
        tempPlayer.clearPowers();
        Boat boat = new Boat();
        tempPlayer.removeCells(borderTop);
        tempPlayer.removeCells(borderButton);
        tempPlayer.removeCells(borderRight);
        tempPlayer.removeCells(borderLeft);
        System.out.println("celdas disponibles: " + tempPlayer.getListCells().size());
        System.out.println(OriginalListCells.size());
        for (int type : entityList) {
            actualBoat = null;
            actualPowerUp = null;
            String coord = "";
            List<String> coordsList = new ArrayList<>();
            //Crea barco
            tempPlayer.setTypeAttack(true);
            if (type == 0) {
                tempPlayer.setTypeAttack(false);
                actualPowerUp = new Mine(coord);
            } else {
                boat = new Boat(type);
                int rotation = random.getRandomNumber(3);
                boat.setRotation(rotation);
                boat.setOrientation((rotation == 1 || rotation == 3));
                actualBoat = boat;
            }
            //Generar coordenadas
            do {
                coord = random.getRandomCoord(tempPlayer);
                coordsList = coordToCells(coord);
            } while (!validateCellPosition(coord, tempPlayer) || !validateCells(coordsList, tempPlayer));
            //Establer coordenadas
            if (type == 1) {
                boat.setCoords(Arrays.asList(coord));

            } else if (type > 1 && type < 4) {
                boat.setCoords(coordsList);
            }
            if (type == 0) {
                //Mina
                tempPlayer.addPower(actualPowerUp);
            } else {
                tempPlayer.addBoat(boat);
            }
            //Agergar celdas
            List<String> coordList = Arrays.asList(coord);
            tempPlayer.addCell(type > 1 ? coordsList : coordList);
            //Ver
            paintCells(!tempPlayer.getName().equals("Bot"), type > 1 ? coordsList : coordList);

        }
        tempPlayer.setTypeAttack(true);
        actualBoat = null;
        displayBoatInformation();
    }

    /**
     * Metodo play
     */
    private void play() {
        System.out.println("Juego");
        //Limpio copyOfEntityList para ser usada en otros metodos
        copyOfEntityList.clear();
        actualBoat = null;
        actualPowerUp = null;
        //Inicia el juego
        player.setTypeAttack(true);
        Panel.setVisible(false);
        PanelGame.setVisible(true);
        //Limpiar celdas visuales y abstractas
        clearAllCells();
        enemy.clearCells();
        player.clearCells();
        inBuild = false;
        //IA vs Player
    }

    /**
     * Asigna la entidad actual y cambia a la siguiente:
     *
     * Si la lista de entidades no está vacía la copia y selecciona la primera
     * entidad. Si es una mina, establece el tipo de ataque del jugador como
     * falso y asigna la entidad. Si es un barco, crea una nueva instancia de
     * barco con el tipo correspondiente. Luego, elimina la entidad de la lista.
     *
     * Si la lista está vacía, inicia el juego estableciendo el tipo de ataque
     * del jugador como verdadero.
     */
    private void changeEntity() {
        displayBoatInformation();
        if (copyOfEntityList.isEmpty() && inBuild) {
            copyOfEntityList.addAll(entityList);
        }
        if (!copyOfEntityList.isEmpty()) {
            int actualEntity = copyOfEntityList.getFirst();
            //Mina
            if (actualEntity == 0) {
                player.setTypeAttack(false);
                actualPowerUp = new Mine();
            } else {
                actualBoat = new Boat(actualEntity);
            }
            //Elimino de la lista la entidad actual
            copyOfEntityList.removeFirst();
            if (copyOfEntityList.isEmpty()) {
                inBuild = false;
            }
        } else {
            play();
        }
    }

    /**
     * Metodo de dibujar impacto, registrando la coordenada usada
     *
     * @param boat Objeto barco a editar
     * @param coord Coordenada de impacto
     * @param tempPlayer Objeto Player (Jugador atacante actual del truno)
     */
    private void drawImpact(int idBoat, String coord, Player tempPlayer) {
        boolean isBot = tempPlayer.getName().equalsIgnoreCase("bot") ? true : false;
        System.out.println(idBoat);
        if (idBoat >= 0) {
            Boat boat = isBot ? player.getBoat(idBoat) : enemy.getBoat(idBoat);
            if (boat != null) {
                //Obtener indice de impacto
                int index = -1;
                for (int i = 0; i < boat.getCoords().size(); i++) {
                    String tempCoords = boat.getCoords().get(i);
                    if (tempCoords.equals(coord)) {
                        index = i;
                        break;
                    }
                }
                //Registrar celda jugada en el jugadro actual
                actualBoat = boat;
                //Reasigno valores de getImage
                if (isBot) {
                    getCellBoard(coord).setBackground(Color.RED);
                } else {
                    getCell(coord).setIcon(img.getIcon(true, index, actualBoat, actualPowerUp, inBuild));
                }
            }
        } else if (idBoat == -2) {
            if (isBot) {
                getCellBoard(coord).setBackground(Color.CYAN);
            } else {
                getCell(coord).setIcon(img.getIcon(true, 10, actualBoat, actualPowerUp, inBuild));
            }
        } else if (idBoat == -3) {
            //mina
            boolean actualAttack = player.getTypeAttack();
            player.setTypeAttack(false);
            getCell(coord).setIcon(img.getIcon(true, 10, actualBoat, new Mine(), true));
            player.setTypeAttack(actualAttack);
            //bot
            getCellBoard(coord).setBackground(Color.YELLOW);
        } else {
            getCellBoard(coord).setBackground(Color.PINK);
            System.out.println("\n\nERROR: " + idBoat);
        }
        actualBoat = null;
    }

    /**
     * Verifica el impacto en una coordenada específica durante un ataque del
     * jugador.
     *
     * @param coord La coordenada a verificar.
     * @param attackPlayer El jugador que realiza el ataque.
     * @param targetPlayer El jugador que es objetivo del ataque.
     * @return Un valor entero que indica el resultado del impacto:
     * <ul>
     * <li>{@code -1} si la coordenada ya fue jugada anteriormente.</li>
     * <li>{@code -2} si la coordenada no contiene un barco.</li>
     * <li>{@code -3} si la coordenada contiene una mina.</li>
     * <li>El ID del barco (valor entero no negativo) si la coordenada contiene
     * un barco.</li>
     * </ul>
     * @throws NullPointerException Si el jugador objetivo no tiene botes
     * disponibles.
     */
    public int impactVerification(String coord, Player attackPlayer, Player targetPlayer) {
        List<Object> boats = targetPlayer.getBoatList();
        if (coord.isEmpty()) {
            System.out.println("\n\nCoord empty");
        }
        if (boats.isEmpty()) {
            throw new NullPointerException("El Jugador: " + targetPlayer.getName() + " no tiene botes disponibles");
        } else if (!targetPlayer.getPowers().isEmpty() && targetPlayer.findPowerCoord(coord)) {
            //Mina
            attackPlayer.addCell(coord);
            targetPlayer.addCell(coord);
            return -3;
        }
        for (int i = 1; i < boats.size(); i += 2) {
            if (((Boat) boats.get(i)).getCoords().contains(coord)) {
                attackPlayer.addCell(coord);
                //Id
                return i - 1;
            }
        }
        //Celda sin barco
        attackPlayer.addCell(coord);
        return -2;
    }

    /**
     * Realiza un ataque por parte del enemigo.
     *
     *
     */
    private void enemyActtack() {
        String coord = "";
        int idBoat = -1;
        actualPowerUp = null;
        //Dificultades
        try {
            switch (DIFFICULTY) {
                case 0 -> {
                    //Coordenada aleatoria
                    coord = random.getRandomCoord(enemy);
                    if (coord == null) {
                        finalGame();
                    }
                    idBoat = impactVerification(coord, enemy, player);
                }
                case 1 -> {
                    actualPowerUp = new Locator();

                    coord = random.getRandomCoord(enemy);
                    idBoat = impactVerification(coord, enemy, player);
                }
                case 2 -> {
                    //Celdas priorizadas
                    if (!priorityCellsEnemy.isEmpty()) {
                        idBoat = impactVerification(priorityCellsEnemy.getFirst(), enemy, player);
                        priorityCellsEnemy.removeFirst();
                        //Valido si hubo impacto y rejecuto
                        if (idBoat >= 0) {
                            drawImpact(idBoat, coord, enemy);
                            enemyActtack();
                        }
                    } else {
                        //Buscar bote, sino obtener coordenada de player aleatoria y generar 9 coordenas segun la central (Locator) 
                        if (random.getRandomNumber(10) > 3) {
                            System.out.println(copyOfEntityList.size());
                            //posicion del ultimo bote
                            //Establer coordenadas como prioritarias
                            Boat boat = (Boat) player.getBoatList().getLast();
                            priorityCellsEnemy.addAll(boat.getCoords());
                            //Atacar coordenadas
                            enemyActtack();
                            //Dificultad normal
                        } else {
                            actualPowerUp = new Locator();
                            //Coordenada aleatoria
                            coord = random.getRandomCoord(enemy);
                            //Validar coordenadas (Locator)
                            if (coord == null) {
                                finalGame();
                            } else if (validateCellPosition(coord)) {
                                //Coordenada convertida en una lista de 9 coordenadas
                                List<String> coords = coordToCells(coord);
                                System.out.println("coords: " + coords.size());
                                int dificulty = random.getRandomNumber(1, coords.size());
                                for (int i = 0; i < dificulty; i++) {
                                    idBoat = impactVerification(coords.get(i), enemy, player);
                                    //Obtengo coordenada si hay un barco y las agrego coordenadas
                                    if (idBoat >= 0) {
                                        Boat boat = player.getBoat(idBoat);
                                        if (boat != null && !priorityCellsEnemy.containsAll(boat.getCoords())) {
                                            priorityCellsEnemy.addAll(boat.getCoords());
                                        }
                                    }
                                }
                                //Verficar si priorityCellsEnemy tiene alguna coordena del bucle anterior
                                if (!priorityCellsEnemy.isEmpty()) {
                                    enemyActtack();
                                }
                            }
                        }
                    }
                }
            }
            actualPowerUp = null;
            //Validar si impacto un barco o no
            if (coord != null) {
                drawImpact(idBoat, coord, enemy);
            }
            turnChange();
        } catch (NullPointerException e) {
            System.out.println("Null pointer");
        }
    }

    private void finalGame() {
        System.out.println(" final");
    }

    /**
     * Obtiene la nueva posición de la rotación.
     *
     * @param rt El número de la rotación actual.
     * @return El nuevo número de la posición de la rotación.
     */
    private int getNewRotation(int rt) {
        if ((rt + 1) > 3) {
            return 0;
        }
        return rt + 1;
    }

    /**
     * Cambia la orientación de un barco en modo de construcción o la
     * orientación de un submarino en modo de ataque. Actualiza también la
     * rotación del barco o submarino.
     */
    private void changeRotation() {
        if (inBuild && actualBoat != null) {
            actualBoat.setOrientation(!actualBoat.getOrientation());
            int rotation = actualBoat.getRotation();
            actualBoat.setRotation(getNewRotation(rotation));
        } else if (player.getTypeAttack() == false && actualPowerUp instanceof Submarine) {
            Submarine temp = (Submarine) actualPowerUp;
            temp.setOrientation(!temp.getOrientation());
            int rt[] = {getNewRotation(temp.getRotationTorpedoes()[0]), getNewRotation(temp.getRotationTorpedoes()[1])};
            temp.setRotation(getNewRotation(temp.getRotation()));
            temp.setRotationTorpedoes(rt);
        }
    }

    /**
     * Maneja el evento de pasar el mouse sobre una celda en el tablero, para
     * dibujar el "fantasma" de la entidad.
     *
     * @param cellName El nombre de la celda sobre la que se ha pasado el mouse.
     */
    private void mouseHover(String cellName) {
        if (!inBuild && validateCells(Arrays.asList(cellName), player)) {
            if (validateCellPosition(cellName)) {

                paintCells(false, cellName);
            } else {
                clearGhostCells();
            }
        }
        if (actualBoat == null) {
            return;
        }
        //Verificar que no este usada
        List<String> coords = (!(actualPowerUp instanceof Locator || actualPowerUp instanceof Submarine) || actualBoat.getSize() > 1) ? coordToCells(cellName) : Arrays.asList(cellName);
        if (validateCells(coords, player)) {
            //Para barcos de 2 y 3 celdas
            if (validateCellPosition(cellName)) {
                paintCells(coordToCells(cellName));
            } else {
                clearGhostCells();
            }

        }
    }

    /**
     * Devuelve las dimensiones totales del Jpanel, incluyendo el espacio del
     * panel y el espacio del tablero.
     *
     * @return Dimensiones totales del panel.
     */
    public Dimension getDimension() {
        return new Dimension(getDimensionCells().width + 250 + Gap, getDimensionCells().height + Gap);
    }

    /**
     * Devuelve las dimensiones del tablero de celdas.
     *
     * @return Dimensiones del tablero de celdas.
     */
    public Dimension getDimensionCells() {
        int r = (ButtonSize * Size) + ButtonSize;
        return new Dimension(r, r);
    }

    /**
     * Inicializa las celdas del panel con botones para representar el tablero
     * de juego.
     */
    private void initCells() {
        List<String> listCells = new ArrayList<>();
        final String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

        final Dimension sizeButton = new Dimension(ButtonSize, ButtonSize);
        GridLayout gridLayout = new GridLayout(Size, Size);
        Board.setLayout(gridLayout);
        gridLayout.setHgap(Gap);
        gridLayout.setVgap(Gap);
        TablePanel.setLayout(gridLayout);
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                String cellName = letters[j] + (i + 1);
                JButton cell = new JButton();
                OriginalListCells.add(cellName);
                cell.setName(cellName);
                cell.setSize(sizeButton);
                cell.setMaximumSize(sizeButton);
                cell.setMinimumSize(sizeButton);
                cell.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton clickedButton = (JButton) e.getSource();
                        // Acción a realizar cuando se hace clic en el botón
                        //System.out.println("Clic en la celda: " + clickedButton.getText());
                        PositionClick(clickedButton);
                    }
                });
                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        JButton enteredButton = (JButton) e.getSource();
                        // Acción a realizar cuando el mouse entra en el botón
                        // System.out.println("Mouse sobre la celda: " + enteredButton.getText());
                        mouseHover(enteredButton.getName());
                    }
                });
                //Agregar a lista JButton
                buttons.add(cell);
                cell.setIcon(VOID_CELL);
                cell.setBorderPainted(false);
                //Agregar boton al jpanel
                TablePanel.add(cell);
                //Agregar a board
                JPanel panel = new JPanel();
                panel.setBackground(BOARD_BG_COLOR);
                panel.setName("B_" + cellName);
                Board.add(panel);
            }
        }
        player.setListCells(OriginalListCells);
        enemy.setListCells(OriginalListCells);
        //Nuevos tamanos
        TablePanel.setSize(getDimensionCells());
        this.setSize(getDimension());
        TablePanel.repaint();
        Board.repaint();
    }
    private void rotateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rotateButtonMouseClicked
        // TODO add your handling code here:
        changeRotation();
        displayBoatInformation();
    }//GEN-LAST:event_rotateButtonMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        generateCoordsOfBoats(player);
    }//GEN-LAST:event_jButton2MouseClicked

    private void rotateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotateButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rotateButtonActionPerformed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        // TODO add your handling code here:
        clearEnetitysOfPlayer(player);
    }//GEN-LAST:event_jButton3KeyPressed

    private void PlayButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayButtonMouseClicked
        play();
    }//GEN-LAST:event_PlayButtonMouseClicked

    private void rotateButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rotateButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rotateButton1MouseClicked

    private void rotateButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotateButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rotateButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Board;
    private javax.swing.JLabel ImageBoat;
    private javax.swing.JLabel L_Healtd;
    private javax.swing.JLabel L_Orientation;
    private javax.swing.JLabel L_Size;
    private javax.swing.JPanel Panel;
    private javax.swing.JPanel PanelGame;
    private javax.swing.JButton PlayButton;
    private javax.swing.JPanel TablePanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton rotateButton;
    private javax.swing.JButton rotateButton1;
    // End of variables declaration//GEN-END:variables
}
