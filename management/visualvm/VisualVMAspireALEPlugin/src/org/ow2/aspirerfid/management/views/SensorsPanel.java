/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SensorsPanel.java
 *
 * Created on 22 févr. 2010, 01:31:42
 */
package org.ow2.aspirerfid.management.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.FixedMillisecond;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeries;
import org.openide.util.Exceptions;
import org.ow2.aspirerfid.management.model.PluginModel;
import org.ow2.aspirerfid.management.model.SensorCacheData;

/**
 *
 * @author Kiev
 */
public class SensorsPanel extends javax.swing.JPanel implements PropertyChangeListener {

    private PluginModel pluginModel;
    private ClassLoader trick;
    private ChartPanel chartPanel;

    /** Creates new form SensorsPanel */
    public SensorsPanel() {
        initComponents();
    }

    public SensorsPanel(PluginModel model) {
        this.pluginModel = model;
        this.pluginModel.addPropertyChangeListener(this);
        initComponents();
        setModel();
        trick = Thread.currentThread().getContextClassLoader();
        valueLabel.setVisible(false);
        unitLabel.setVisible(false);
        new Thread("Update Sensors Info Thread") {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        updateSensorInfo();
                    } catch (InterruptedException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }

            }

        }.start();
    }

    private void updateSensorInfo() {
        ClassLoader temp = Thread.currentThread().getContextClassLoader();

        try {
            Thread.currentThread().setContextClassLoader(trick);
            String sensorName = (String) sensorsList.getSelectedValue();
            if (sensorName != null) {
                List<SensorCacheData> cacheData = pluginModel.getSensorData(sensorName);
                valueLabel.setText("" + cacheData.get(cacheData.size() - 1).getValue());
                unitLabel.setText("" + cacheData.get(cacheData.size() - 1).getUnit());
                valueLabel.setVisible(true);
                unitLabel.setVisible(true);
                if (chartPanel == null) {
                    chartPanel = new ChartPanel(getChart(cacheData));
                    //chartPanel.setPreferredSize(new Dimension(300, 200));
                    chartPanel.setVisible(true);
                    chartPanel.setOpaque(false);
                    chartViewPanel.setLayout(new BorderLayout());
                    chartViewPanel.add(chartPanel, BorderLayout.CENTER);

                } else {
                    chartPanel.setChart(getChart(pluginModel.getSensorData(sensorName)));
                }
                chartPanel.invalidate();
                chartPanel.repaint();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            Thread.currentThread().setContextClassLoader(temp);
        }
    }

    private void setModel() {
        sensorsList.setModel(new javax.swing.AbstractListModel() {

            String[] strings = pluginModel.getSensorList();

            public int getSize() {
                return strings.length;
            }

            public Object getElementAt(int i) {
                return strings[i];
            }
        });
        //TODO SelectionModel must change the labels that display data of current item
        sensorsList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                updateSensorInfo();
            }
        });
    }

    public JFreeChart getChart(List<SensorCacheData> data) {
        JFreeChart chart = null;
        String presentationName = "Measurement evolution";
        String unit = null;

        if (data.size() != 0) {
            TimeSeries series1 = new TimeSeries(presentationName,
                    FixedMillisecond.class);
            double pos = 0;
            double valMin = Integer.MAX_VALUE; //data.get(0).getMeasurement(measurement).getValue();
            double valMax = Integer.MIN_VALUE; //valMin;
            for (SensorCacheData bean : data) {
                if (bean != null) {
                    double value = bean.getValue();
                    unit = bean.getUnit();
                    series1.add(new FixedMillisecond(new Date(bean.getTimestamp())), value);
                    pos++;
                    if (value > valMax) {
                        valMax = value;
                    }
                    if (value < valMin) {
                        valMin = value;
                    }
                }
            }

            TimeSeriesCollection dataset = new TimeSeriesCollection();
            dataset.addSeries(series1);
            String yAxisLabel = "Unit " + ((unit == null || unit.equals("") || unit.equals("unity")) ? "" : " (" + unit + ")");
            chart = ChartFactory.createTimeSeriesChart(presentationName, // title
                    "Date/Time", // x-axis label
                    yAxisLabel, // y-axis label
                    dataset, // data
                    true, // create legend?
                    true, // generate tooltips?
                    false // generate URLs?
                    );
            chart.setBackgroundPaint(Color.WHITE);
            // // get a reference to the plot for further customisation...
            XYPlot plot = chart.getXYPlot();
            // plot.setBackgroundPaint(Color.lightGray);
            // plot.setDomainGridlinePaint(Color.white);
            // plot.setRangeGridlinePaint(Color.white);

            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
            renderer.setSeriesLinesVisible(0, true);
            renderer.setSeriesShapesVisible(1, true);
            plot.setRenderer(renderer);

            // change the auto tick unit selection to integer units only...
            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setRange(valMin - 1, valMax + 1);

        }
        return chart;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        sensorsList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        valueLabel = new javax.swing.JLabel();
        unitLabel = new javax.swing.JLabel();
        chartViewPanel = new javax.swing.JPanel();

        setOpaque(false);

        sensorsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        sensorsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(sensorsList);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13));
        jLabel1.setLabelFor(sensorsList);
        jLabel1.setText(org.openide.util.NbBundle.getMessage(SensorsPanel.class, "SensorsPanel.jLabel1.text")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(SensorsPanel.class, "SensorsPanel.jPanel1.border.title"))); // NOI18N
        jPanel1.setOpaque(false);

        jLabel2.setText(org.openide.util.NbBundle.getMessage(SensorsPanel.class, "SensorsPanel.jLabel2.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(SensorsPanel.class, "SensorsPanel.jLabel3.text")); // NOI18N

        valueLabel.setText(org.openide.util.NbBundle.getMessage(SensorsPanel.class, "SensorsPanel.valueLabel.text")); // NOI18N

        unitLabel.setText(org.openide.util.NbBundle.getMessage(SensorsPanel.class, "SensorsPanel.unitLabel.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(unitLabel)
                    .addComponent(valueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(valueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(unitLabel))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        chartViewPanel.setOpaque(false);

        javax.swing.GroupLayout chartViewPanelLayout = new javax.swing.GroupLayout(chartViewPanel);
        chartViewPanel.setLayout(chartViewPanelLayout);
        chartViewPanelLayout.setHorizontalGroup(
            chartViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        chartViewPanelLayout.setVerticalGroup(
            chartViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 322, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chartViewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chartViewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartViewPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList sensorsList;
    private javax.swing.JLabel unitLabel;
    private javax.swing.JLabel valueLabel;
    // End of variables declaration//GEN-END:variables

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(PluginModel.SENSOR_PROPERTY)) {
            if (!Arrays.deepEquals(
                    (String[])evt.getNewValue(),
                    (String[])evt.getOldValue())){
            SwingUtilities.invokeLater(new Runnable() {
             public void run() {

               System.out.println("setting model");
               setModel();
             }
            });
            }
        }
    }
}
