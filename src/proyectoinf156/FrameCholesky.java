/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoinf156;

import java.awt.GridLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author RuCo
 */
public class FrameCholesky extends javax.swing.JInternalFrame {
    
    private String etapas;
    double[][] matriz;
    int filas;
    int columnas;
    String proceso = "";
    String solucion="";

    /**
     * Creates new form FrameCholesky
     */
    
    
    public FrameCholesky(double[][] matriz, int filas, int columnas) {
        initComponents();
        this.matriz = matriz;
        this.filas = filas;
        this.columnas = columnas;
        calcularCholesky(matriz);
    }
    
    public void calcularCholesky(double[][] matriz) {
        int n = matriz.length;

        double[] resultadoz = new double[n];
        double[] resultadox = new double[n];
        double[][] matrizl = new double[n][n];
        double[][] matrizu = new double[n][n];

        for (int i = 0; i < n; ++i) {
            matrizu[i][i] = 1.0D;
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                double suma = 0.0D;
                for (int k = 0; k < n; ++k) {
                    suma += matrizl[i][k] * matrizu[k][j];
                }
                if (j < i) {
                    matrizl[i][j] = ((matriz[i][j] - suma) / matrizu[j][j]);
                } else if (j > i) {
                    matrizu[i][j] = ((matriz[i][j] - suma) / matrizl[i][i]);
                } else {
                    matrizu[i][j] = Math.sqrt(Math.abs(matriz[i][i] - suma));
                    matrizl[i][j] = Math.sqrt(Math.abs(matriz[i][i] - suma));
                }
            }
            PanelEtapasLU panelEtapas = new PanelEtapasLU();
            panelEtapas.setEtapa("Etapa " + (i + 1));
            panelEtapas.setModeloL(matrizl, filas, columnas - 1);
            panelEtapas.setModeloU(matrizu, filas, columnas - 1);
            panel.setLayout(new GridLayout(i + 1, 1));
            panel.add(panelEtapas);
            setSize(getWidth(), getHeight() + 1);
            setSize(getWidth(), getHeight() - 1);
        }
        for (int i = 0; i < n; ++i) {
            double suma = 0.0D;
            for (int j = i - 1; j >= 0; --j) {
                suma += matrizl[i][j] * resultadoz[j];
            }
            resultadoz[i] = ((matriz[i][n] - suma) / matrizl[i][i]);
        }
        
        this.proceso += "\n";
        for (int i = n - 1; i >= 0; --i) {
            double suma = 0.0D;
            for (int j = i + 1; j < n; ++j) {
                suma += matrizu[i][j] * resultadox[j];
            }
            resultadox[i] = ((resultadoz[i] - suma) / matrizu[i][i]);
        }
        
        String resultados = "";
        String resultadoLblX = "";
        for (int i = 0; i < n; ++i) {
            resultados = (resultados + "Z" + (i + 1) + "= " + resultadoz[i] + "\n");
        }
        for (int i = 0; i < n; ++i) {
            resultados = (resultados + "X" + (i + 1) + "= " + resultadox[i] + "\n");
            //
            resultadoLblX = (resultadoLblX + "X" + (i + 1) + "= " + resultadox[i] + " \n");
            
        }
        solucion = resultadoLblX;
        panelSoluciones panelSln = new panelSoluciones();
        panelSln.setSoluciones(resultados);
        panel.setLayout(new GridLayout(n+1, 1));
        panel.add(panelSln);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        soluciones = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();

        soluciones.setText("Soluciones");
        soluciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solucionesActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Cholesky");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Procedimiento"));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(23, 23));
        jScrollPane1.setName("jScrollPanel1");
        jScrollPane1.setPreferredSize(new java.awt.Dimension(584, 525));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 582, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 193, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(216, 216, 216))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(soluciones)
                                .addGap(215, 215, 215))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(soluciones)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void solucionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solucionesActionPerformed
        // TODO add your handling code here:
        
        JOptionPane.showMessageDialog(this,"Las Soluciones del Sistema son: \n"+solucion);
    }//GEN-LAST:event_solucionesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    private javax.swing.JButton soluciones;
    // End of variables declaration//GEN-END:variables
}