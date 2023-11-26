package zes.core.engine.utils;

import java.util.ArrayList;

public class ZStack <A> {
	private ArrayList<A> list;
	
	public ZStack() {
		list = new ArrayList();
	}
	
	public void push(A value) {
		list.add(value);
	}
	
	public A pop() {
		return list.remove(getEndIndex());
	}
	
	public A peek() {
		return list.get(getEndIndex());
	}
	
	public int getEndIndex() {
		return list.size() - 1;
	}
	
	public int size() {
		return list.size();
	}
}
