package symboltable;

import java.util.*;
import ast.definitions.Definition;

public class SymbolTable {
	
	private int scope=0;
	private List<Map<String,Definition>> table;
	public SymbolTable()  {
		this.table=new ArrayList<>();
		this.table.add(new HashMap<>());
	}

	public void set() {
		this.scope++;
		this.table.add(new HashMap<>());
	}
	
	public void reset() {
		if(this.scope==0) {
			throw new IllegalStateException("El scope no puede ser menor que 0");
		}
		this.scope--;
		this.table.remove(table.size()-1);
	}
	
	public boolean insert(Definition definition) {
		if(this.table.get(this.scope).containsKey(definition.getName())) {
			return false;
		}
		this.table.get(this.scope).put(definition.getName(),definition);
		definition.setScope(this.scope);
		return true;
	}
	
	public Definition find(String id) {
		int currentScope=this.scope;
		while(currentScope>=0) {
			Definition definition=this.table.get(currentScope).get(id);
			if(definition!=null) {
				return definition;
			}
			currentScope--;
		}
		return null;
	}
	//package-protected for testing pourposes
	boolean findInCurrentScope(String id) {
		return this.table.get(this.scope).get(id)!=null;
	}
}
