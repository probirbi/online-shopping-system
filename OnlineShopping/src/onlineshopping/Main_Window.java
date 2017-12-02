/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineshopping;

import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Probir
 */
public class Main_Window extends javax.swing.JFrame {

    /**
     * Creates new form NewJPanel
     */
    public Main_Window() {
        initComponents();
        Show_Products_In_JTable();
        //getConnection();
    }

    String imgPath = null;
    int pos;

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/products_db2", "root", "");
            JOptionPane.showMessageDialog(null, "Connected");
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Not Connected");
            return null;
        }

    }

    //Check Input Fields
    public boolean checkInputs() {
        if (txt_name.getText() == null || txt_price.getText() == null || txt_AddDate.getDate() == null) {
            return false;
        } else {
            try {
                Float.parseFloat(txt_price.getText());
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }

    //Resize Image
    public ImageIcon ResizeImage(String imagePath, byte[] pic) {
        ImageIcon myImage = null;
        if (imagePath != null) {
            myImage = new ImageIcon(imagePath);
        } else {
            myImage = new ImageIcon(pic);
        }

        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }

    // Display Data IN JTable
    //   1-Fill ArrayList with The Data
    public ArrayList<Products> getProductList() {
        ArrayList<Products> productList = new ArrayList<Products>();
        Connection con = getConnection();
        String query = "SELECT * FROM products";

        Statement st;
        ResultSet rs;

        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            Products products;

            while (rs.next()) {
                products = new Products(rs.getInt("id"), rs.getString("name"), Float.parseFloat(rs.getString("price")), rs.getString("add_date"), rs.getBytes("image"));
                productList.add(products);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productList;
    }

    //   2- Populate The JTable
    public void Show_Products_In_JTable() {
        ArrayList<Products> list = getProductList();
        DefaultTableModel model = (DefaultTableModel) JTable_Products.getModel();

        //clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getPrice();
            row[3] = list.get(i).getAddDate();

            model.addRow(row);
        }
    }

    public void ShowItem(int index) {
        txt_id.setText(Integer.toString(getProductList().get(index).getId()));
        txt_name.setText(getProductList().get(index).getName());
        txt_price.setText(Float.toString(getProductList().get(index).getPrice()));

        try {
            Date addDate = null;
            addDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse((String) getProductList().get(index).getAddDate());
            txt_AddDate.setDate(addDate);

        } catch (ParseException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbl_image.setIcon(ResizeImage(null, getProductList().get(index).getImage()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jFrame3 = new javax.swing.JFrame();
        jFrame4 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        canvas1 = new java.awt.Canvas();
        txt_AddDate = new com.toedter.calendar.JDateChooser();
        lbl_image = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Products = new javax.swing.JTable();
        Btn_choose_Image = new javax.swing.JButton();
        Btn_Insert = new javax.swing.JButton();
        Btn_Update = new javax.swing.JButton();
        Btn_Previous = new javax.swing.JButton();
        Btn_Next = new javax.swing.JButton();
        Btn_Delete = new javax.swing.JButton();
        Btn_First = new javax.swing.JButton();
        Btn_Last = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        //setVisible(true);

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame4Layout = new javax.swing.GroupLayout(jFrame4.getContentPane());
        jFrame4.getContentPane().setLayout(jFrame4Layout);
        jFrame4Layout.setHorizontalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame4Layout.setVerticalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("ID");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Name");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Price");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Add Date");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Image");

        txt_name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_name.setPreferredSize(new java.awt.Dimension(59, 40));

        txt_id.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_id.setEnabled(false);
        txt_id.setPreferredSize(new java.awt.Dimension(59, 40));

        txt_price.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_price.setPreferredSize(new java.awt.Dimension(59, 40));

        txt_AddDate.setDateFormatString("yyyy-MM-dd");
        txt_AddDate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        lbl_image.setBackground(new java.awt.Color(255, 255, 255));
        lbl_image.setOpaque(true);

        JTable_Products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Price", "Add Date"
            }
        ));
        JTable_Products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ProductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Products);

        Btn_choose_Image.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_choose_Image.setText("Image Choose");
        Btn_choose_Image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_choose_ImageActionPerformed(evt);
            }
        });

        Btn_Insert.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project icon/Add_16x16.png"))); // NOI18N
        Btn_Insert.setText("Insert");
        Btn_Insert.setIconTextGap(15);
        Btn_Insert.setMaximumSize(new java.awt.Dimension(97, 33));
        Btn_Insert.setMinimumSize(new java.awt.Dimension(97, 33));
        Btn_Insert.setPreferredSize(new java.awt.Dimension(97, 33));
        Btn_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_InsertActionPerformed(evt);
            }
        });

        Btn_Update.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project icon/Refresh_16x16.png"))); // NOI18N
        Btn_Update.setText("Update");
        Btn_Update.setIconTextGap(15);
        Btn_Update.setMaximumSize(new java.awt.Dimension(97, 33));
        Btn_Update.setMinimumSize(new java.awt.Dimension(97, 33));
        Btn_Update.setPreferredSize(new java.awt.Dimension(97, 33));
        Btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_UpdateActionPerformed(evt);
            }
        });

        Btn_Previous.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Previous.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project icon/Previous_16x16.png"))); // NOI18N
        Btn_Previous.setText("Previous");
        Btn_Previous.setIconTextGap(15);
        Btn_Previous.setMaximumSize(new java.awt.Dimension(97, 33));
        Btn_Previous.setMinimumSize(new java.awt.Dimension(97, 33));
        Btn_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_PreviousActionPerformed(evt);
            }
        });

        Btn_Next.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project icon/Next_16x16.png"))); // NOI18N
        Btn_Next.setText("Next");
        Btn_Next.setIconTextGap(15);
        Btn_Next.setMaximumSize(new java.awt.Dimension(97, 33));
        Btn_Next.setMinimumSize(new java.awt.Dimension(97, 33));
        Btn_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_NextActionPerformed(evt);
            }
        });

        Btn_Delete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project icon/Delete_16x16.png"))); // NOI18N
        Btn_Delete.setText("Delete");
        Btn_Delete.setIconTextGap(15);
        Btn_Delete.setMaximumSize(new java.awt.Dimension(97, 33));
        Btn_Delete.setMinimumSize(new java.awt.Dimension(97, 33));
        Btn_Delete.setPreferredSize(new java.awt.Dimension(97, 33));
        Btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_DeleteActionPerformed(evt);
            }
        });

        Btn_First.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_First.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project icon/Previous_24x24.png"))); // NOI18N
        Btn_First.setText("First");
        Btn_First.setIconTextGap(15);
        Btn_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_FirstActionPerformed(evt);
            }
        });

        Btn_Last.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project icon/Next_24x24.png"))); // NOI18N
        Btn_Last.setText("Last");
        Btn_Last.setIconTextGap(15);
        Btn_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_LastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_AddDate, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(542, 542, 542))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Btn_choose_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(11, 11, 11)
                                            .addComponent(Btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(Btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(Btn_First)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(Btn_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(Btn_Next, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(Btn_Last))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Btn_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(txt_AddDate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(jLabel5))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_choose_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Btn_Delete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Btn_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Btn_Previous, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Btn_Next, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Btn_First, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Btn_Last, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        //setVisible(true);
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_InsertActionPerformed

        if (checkInputs() && imgPath != null) {
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO products (name,price,add_date,image) " + "values (?,?,?,?)");
                ps.setString(1, txt_name.getText());
                ps.setString(2, txt_price.getText());

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String addDate = dateFormat.format(txt_AddDate.getDate());
                //ps.setString(3, addDate);
                ps.setDate(3, Date.valueOf(addDate));
                //System.out.println("AddDate => "+addDate);

                InputStream img = new FileInputStream(new File(imgPath));
                ps.setBlob(4, img);
                ps.executeUpdate();
                Show_Products_In_JTable();

                JOptionPane.showMessageDialog(null, "Data Inserted");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "One or More Field Are Empty");
        }
        //System.out.println("Name => "+txt_name.getText());
        //System.out.println("Price => "+txt_price.getText());
        //System.out.println("AddDate => "+txt_AddDate.getDate());
        //System.out.println("Image => "+imgPath);
    }//GEN-LAST:event_Btn_InsertActionPerformed

    private void Btn_choose_ImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_choose_ImageActionPerformed
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lbl_image.setIcon(ResizeImage(path, null));
            imgPath = path;
        } else {
            System.out.println("No File Selected");
        }
    }//GEN-LAST:event_Btn_choose_ImageActionPerformed

    private void Btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_UpdateActionPerformed
        if (checkInputs() && txt_id.getText() != null) {

            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConnection();

            //update without image
            if (imgPath == null) {
                try {
                    UpdateQuery = "UPDATE products SET name = ?, price = ?"
                            + ", add_date = ? WHERE id = ?";
                    ps = con.prepareStatement(UpdateQuery);
                    ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_price.getText());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateFormat.format(txt_AddDate.getDate());
                    ps.setDate(3, Date.valueOf(addDate));

                    ps.setInt(4, Integer.parseInt(txt_id.getText()));
                    ps.executeUpdate();
                    Show_Products_In_JTable();
                    JOptionPane.showMessageDialog(null, "Product Updated");

                } catch (SQLException ex) {
                    Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                }
            } // update with Image
            else {
                //InputStream img = null;
                try {
                    InputStream img = new FileInputStream(new File(imgPath));
                    UpdateQuery = "UPDATE products SET name = ?, price = ?"
                            + ", add_date = ?, image = ? WHERE id = ?";
                    ps = con.prepareStatement(UpdateQuery);
                    ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_price.getText());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateFormat.format(txt_AddDate.getDate());
                    ps.setDate(3, Date.valueOf(addDate));
                    ps.setBlob(4, img);
                    ps.setInt(5, Integer.parseInt(txt_id.getText()));
                    ps.executeUpdate();
                    Show_Products_In_JTable();
                    JOptionPane.showMessageDialog(null, "Product Updated");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "One or More Fields Are Empty or Wrong");
        }
    }//GEN-LAST:event_Btn_UpdateActionPerformed

    private void Btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_DeleteActionPerformed
        if (!txt_id.getText().equals("")) {
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM products WHERE id=?");
                ps.setInt(1, Integer.parseInt(txt_id.getText()));
                ps.executeUpdate();
                Show_Products_In_JTable();
                JOptionPane.showMessageDialog(null, "Product Deleted");

            } catch (SQLException ex) {
                Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Product Not Deleted");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Product Not Deleted : No Id To Delete");
        }
    }//GEN-LAST:event_Btn_DeleteActionPerformed

    private void JTable_ProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ProductsMouseClicked
        int index = JTable_Products.getSelectedRow();
        ShowItem(index);
    }//GEN-LAST:event_JTable_ProductsMouseClicked

    private void Btn_PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_PreviousActionPerformed
        pos--;
        if (pos < 0) {
            pos = 0;
        }
        ShowItem(pos);
    }//GEN-LAST:event_Btn_PreviousActionPerformed

    private void Btn_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_FirstActionPerformed
        pos = 0;
        ShowItem(pos);
    }//GEN-LAST:event_Btn_FirstActionPerformed

    private void Btn_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_NextActionPerformed
        pos++;
        if (pos >= getProductList().size()) {
            pos = getProductList().size() - 1;
        }
        ShowItem(pos);
    }//GEN-LAST:event_Btn_NextActionPerformed

    private void Btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LastActionPerformed
        pos = getProductList().size() - 1;
        ShowItem(pos);
    }//GEN-LAST:event_Btn_LastActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main_Window().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Delete;
    private javax.swing.JButton Btn_First;
    private javax.swing.JButton Btn_Insert;
    private javax.swing.JButton Btn_Last;
    private javax.swing.JButton Btn_Next;
    private javax.swing.JButton Btn_Previous;
    private javax.swing.JButton Btn_Update;
    private javax.swing.JButton Btn_choose_Image;
    private javax.swing.JTable JTable_Products;
    private java.awt.Canvas canvas1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JFrame jFrame4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_image;
    private com.toedter.calendar.JDateChooser txt_AddDate;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_price;
    // End of variables declaration//GEN-END:variables

}
