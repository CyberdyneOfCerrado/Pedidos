package pedidos.interf;

import pedidos.util.DoAction;


public interface IReflectiveModel {
	public String getTableName();
    public String getColumnName();
    public String getColumnValues();
    public void biuldObject(DoAction doAction);
}
