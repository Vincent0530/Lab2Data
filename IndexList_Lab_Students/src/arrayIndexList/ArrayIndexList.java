package arrayIndexList;

import java.lang.reflect.Array;

import indexList.IndexList;

public class ArrayIndexList<E> implements IndexList<E> {
    private static final int INITCAP = 1; //valor anterior era 5
    private static final int CAPTOAR = 1; //valor anterior era 5
    private static final int MAXEMPTYPOS = 2; //valor anterior era 10
    private E[] element; 
    private int size; 

    public ArrayIndexList() { 
        element = (E[]) new Object[INITCAP]; 
        size = 0; 
    } 
    
    public int capacity() { 
   	 return element.length; 
   }

    

    public void add(int index, E e) throws IndexOutOfBoundsException {
        if (!valid(index, size+1)) {throw new IndexOutOfBoundsException("add: invalid index = " + index);}
        if (size == element.length){
            changeCapacity(CAPTOAR);
        }
//        else if(element.length - size==MAXEMPTYPOS){
//            changeCapacity(-CAPTOAR);
//        }
        for(int i=size-1; i>=index;i--){
            element[i+1] = element[i];
        }
        
        element[index]= e;
        size++;
    }


    public void add(E e) {
        if (size == element.length){
            changeCapacity(CAPTOAR);
        }
//        else if(element.length - size==MAXEMPTYPOS){
//                changeCapacity(-CAPTOAR);
//        }
        element[size]= e;
        size++;
    }


    public E get(int index) throws IndexOutOfBoundsException {
        if (!valid(index, size+1)) {throw new IndexOutOfBoundsException("get: invalid index = " + index);}
        return element[index];
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public E remove(int index) throws IndexOutOfBoundsException {
        if (!valid(index, size+1)) {throw new IndexOutOfBoundsException("remove: invalid index = " + index);}
        
        if(element.length - size==MAXEMPTYPOS){
        	changeCapacity(-CAPTOAR);
        }
        if(isEmpty())
        	return null;
        E etr = element[index];
        for(int i=index+1; i<size;i++){
            element[i-1]= element[i];
        }
        
        element[size-1]=null;
        size--;
        return etr;
    }


    public E set(int index, E e) throws IndexOutOfBoundsException {
        if (!valid(index, size+1)) {throw new IndexOutOfBoundsException("set: invalid index = " + index);}
        E etr = element[index];
        element[index]=e;
        return etr;
    }


    public int size() {
        return size;
    }    
    
    private boolean valid(int index, int size){
        return index >= 0 && index<size;
    }
    
    
    
    // private methods  -- YOU CAN NOT MODIFY ANY OF THE FOLLOWING
    // ... ANALYZE AND USE WHEN NEEDED
    
    // you should be able to decide when and how to use
    // following method.... BUT NEED TO USE THEM WHENEVER
    // NEEDED ---- THIS WILL BE TAKEN INTO CONSIDERATION WHEN GRADING
    
    private void changeCapacity(int change) { 
        int newCapacity = element.length + change; 
        E[] newElement = (E[]) new Object[newCapacity]; 
        for (int i=0; i<size; i++) { 
            newElement[i] = element[i]; 
            element[i] = null; 
        } 
        element = newElement; 
    }
    
    // useful when adding a new element with the add
    // with two parameters....
    private void moveDataOnePositionTR(int low, int sup) { 
        // pre: 0 <= low <= sup < (element.length - 1)
        for (int pos = sup; pos >= low; pos--)
            element[pos+1] = element[pos]; 
    }

    // useful when removing an element from the list...
    private void moveDataOnePositionTL(int low, int sup) { 
        // pre: 0 < low <= sup <= (element.length - 1)
        for (int pos = low; pos <= sup; pos++)
            element[pos-1] = element[pos]; 
    }


    // The following two methods are to be implemented as part of an exercise
    public Object[] toArray() {
        Object[] arr = new Object[size];
        for(int i=0; i<size;i++){
        	arr[i]=element[i];
        }
        return arr;
    }


    @Override
    public <T1> T1[] toArray(T1[] array) {
        if(array.length< this.size){
        	array = (T1[]) Array.newInstance(array.getClass().getComponentType(), this.size());
        	
        }
        
        else if (array.length > this.size()) 
	    	
	    	for (int j=this.size(); j< array.length; j++) 
	    		array[j] = null;
        
        for(int i=0; i<size;i++){
        	array[i]=(T1)element[i];
        }
        return array;
        
    }

}

