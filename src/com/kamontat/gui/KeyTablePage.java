package com.kamontat.gui;

import com.kamontat.code.constant.HotKey;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class KeyTablePage extends JDialog {
	private JPanel contentPane;
	private JButton buttonOK;
	private JTable table;
	
	public KeyTablePage() {
		super((Frame) null, "Key Table Page");
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);
		
		table.setCellSelectionEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(addTableModel());
		addActionListener();
		
		buttonOK.addActionListener(e -> onOK());
	}
	
	public static void addKeyTo(Window w, JPanel p) {
		Action key = KeyTablePage.getAction(w.getLocation());
		p.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(HotKey.KEY.getKeyStroke(), "key");
		p.getActionMap().put("key", key);
	}
	
	private static Action getAction(Point p) {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new KeyTablePage().run(p);
			}
		};
	}
	
	private TableModel addTableModel() {
		// create own model
		class MyModel extends DefaultTableModel {
			private MyModel(Object[][] data, Object[] columnNames) {
				super(data, columnNames);
			}
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		}
		
		String[] column = new String[]{"Name", "Description", "Where", "When", "Shortcut Key"};
		
		String[][] allData = new String[HotKey.getTotalKey()][5];
		int i = 0;
		for (HotKey hk : HotKey.values()) {
			allData[i++] = hk.getAll();
		}
		sort(allData);
		
		return new MyModel(allData, column);
	}
	
	private void addActionListener() {
		KeyTablePage self = this;
		table.addMouseListener(new MouseAdapter() {
			private boolean mousePressed = false;
			private Popup pu;
			
			@Override
			public void mousePressed(MouseEvent e) {
				JTable target = (JTable) e.getSource();
				int row = target.getSelectedRow();
				int column = target.getSelectedColumn();
				
				new Thread(() -> {
					pu = new Popup(String.valueOf(target.getValueAt(row, column)));
					mousePressed = true;
					while (mousePressed) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						if (mousePressed) {
							pu.popup(e.getLocationOnScreen());
						}
					}
				}).start();
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				mousePressed = false;
				pu.cancel();
			}
		});
	}
	
	private void sort(String[][] a) {
		Arrays.sort(a, (o1, o2) -> o1[0].compareTo(o2[0]));
	}
	
	private void onOK() {
		// add your code here
		dispose();
	}
	
	public void run(Point point) {
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
