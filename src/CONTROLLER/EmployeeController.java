package CONTROLLER;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.EmployeeGUI;

public class EmployeeController implements ActionListener{
	private EmployeeGUI employeeGUI;

	public EmployeeController(EmployeeGUI employeeGUI) {
		this.employeeGUI = employeeGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		switch (cmd) {
		case "THÊM":
			employeeGUI.addCustomer();
			break;
		case "XÓA":
			employeeGUI.deleteCustomer();
			break;
		case "SỬA":
			
			break;
		case "CẬP NHẬT":
			
			break;
		default:
			break;
		}
	}
}
