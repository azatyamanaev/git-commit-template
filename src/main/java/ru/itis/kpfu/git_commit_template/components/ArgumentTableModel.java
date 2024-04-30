package ru.itis.kpfu.git_commit_template.components;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import ru.itis.kpfu.git_commit_template.models.Argument;

public class ArgumentTableModel extends DefaultTableModel {

    public List<Argument> rows;

    public List<TableRowDefinition<Argument>> definitions;

    public ArgumentTableModel() {
        super(20, 2);
        this.rows = new ArrayList<>(20);
        this.definitions = List.of( new TableRowDefinition<>("Name", Argument::getName),
                new TableRowDefinition<>("Value", Argument::getValue));
    }

    @Override
    public int getRowCount() {
        if (rows == null) rows = new ArrayList<>(20);
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return definitions.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Argument row = rows.get(rowIndex);
        return definitions.get(columnIndex).tableModelRowFunction.apply(row);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Argument row = rows.get(rowIndex);
        if (columnIndex == 0) {
            row.setName((String) aValue);
        } else if (columnIndex == 1) {
            row.setValue((String) aValue);
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return definitions.get(columnIndex).title;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public static void updateTable(ArgumentTableModel tableModel, List<Argument> arguments) {
        tableModel.rows.clear();
        if (arguments != null && !arguments.isEmpty()) {
            tableModel.rows.addAll(arguments);
        }
    }
}
