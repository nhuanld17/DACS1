package CONTROLLER;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.ManagerGUI;

public class ManagerController implements ActionListener{
	private ManagerGUI managerGUI;
	
	
	public ManagerController(ManagerGUI managerGUI) {
		this.managerGUI = managerGUI;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand();
		
		switch (name) {
		case "THÊM":
			managerGUI.addEmployee();
			break;
		case "XÓA":
			managerGUI.deleteEmployee();
			break;
		case "SỬA":
			managerGUI.setForm();
			break;
		case "CẬP NHẬT":
			managerGUI.updateEmployee();
			break;
		default:
			break;
		}
	}
	
}
