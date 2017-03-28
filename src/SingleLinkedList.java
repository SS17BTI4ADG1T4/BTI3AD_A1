



public class SingleLinkedList<T> implements OwnList<T>{
	//TODO Size umschreiben sodass er durchz�hlt?
	private int Size=0;
	private Elem startElem;
	private Elem endElem ;
	private Counter  counter = null;
	
	
	
	/**
	 * Constructor
	 */
	public SingleLinkedList() {
		startElem = new Elem();
		count();
		endElem = new Elem();
		count();
		startElem.setNext(endElem);
		count();
	}
	
	public SingleLinkedList(Counter counter){
		this();
		this.counter = counter;
	}
	/**
	 * Adds an  new Element to the List
	 * @param insertElem
	 * @throws ElementNotFoundException
	 */
	public void add(Elem<T> insertElem) throws ElementNotFoundException{
		if(Size==0){
			startElem.setNext(insertElem);
			count();
			insertElem.setNext(endElem);
			count();
		}else{
			Elem elem = startElem;
			count();
			while(elem.getNext()!=endElem){
				elem=elem.getNext();
				count();
			}
			elem.setNext(insertElem);
			count();
			insertElem.setNext(endElem);
			count();
		}
		Size++;
		count();
	}

	/**
	 * Inserts an Element at a given Positon
	 * @param insertPos position of the Insertion
	 * @param insertElem Element to be inserted
	 * @throws IndexOutOfBoundsException
	 */
	public void insert(Pos insertPos, Elem insertElem) throws IndexOutOfBoundsException {
		if(insertPos.getPos()>Size){
			count();
			throw new IndexOutOfBoundsException();
		}
		if(insertPos.getPos()<0){
			count();
			throw new IndexOutOfBoundsException();
		}
		int index =0;
		count();
		Elem prev = startElem;
		count();
		while(index<insertPos.getPos()){
			prev=prev.getNext();
			count();
			index++;
			count();
		}
		Elem after = prev.getNext();
		count();
		prev.setNext(insertElem);
		count();
		insertElem.setNext(after);
		count();
		Size++;	
		count();
	}

	/**
	 * Deletes an Element at a given position
	 * @param deletePos
	 * @throws IndexOutOfBoundsException
	 */
	public void delete(Pos deletePos) throws IndexOutOfBoundsException {
		if(deletePos.getPos()>Size){
			count();
			throw new IndexOutOfBoundsException();
		}
		if(Size==0){
			count();
			throw new IndexOutOfBoundsException();
		}
		if(deletePos.getPos()<0){
			count();
			throw new IndexOutOfBoundsException();
		}
		Elem<T> elem = startElem;
		count();
		Elem<T> prev = new Elem<T>();
		count();
		 Pos position = new Pos(0);
		 count();
	        while (position.getPos()<deletePos.getPos()){
	        	position.set(position.getPos()+1);//erh�he position um 1
	        	count();
	        	prev = elem; // setze als vorheriges elem das aktuelle
	        	count();
	        	elem = elem.getNext(); //gehe ein elem weiter
	        	count();
	        }
	        Elem<T> temp = elem.getNext(); //nimm das element nach dem zu l�schendem
	        count();
	        elem.setData(null); //entferne den Inhalt des zu l�schenden Obj
	        count();
	        prev.setNext(temp);// setzte den Zeiger von dem elem vor dem zu l�schenden auf das elem nach dem gel�schten
	        count();
	        Size--; 
	        count();
	}

	/**
	 * Deletes an Element by a given Key
	 * The Key is the data of the Element
	 * @param deleteKey
	 * @throws ElementNotFoundException 
	 */
	public void delete(T deleteKey) throws ElementNotFoundException {
		Elem<T> temp = new Elem(deleteKey);
		count();
		Elem<T> prev = startElem;
		count();
		addStopAtEnd(temp);
		count();
		while(!prev.getNext().equals(temp)){
			prev =prev.getNext();
			count();
		}
		if(prev.getNext().getNext()==endElem){
			removeStopAtEnd();
			count();
			throw new ElementNotFoundException();
		}else{
			temp=prev.getNext().getNext();
			count();
			prev.setNext(temp);
			count();
		}
		Size--;
		count();
		
	}
	
	/**
	 * Finds the previous Element to a given Element(exakt) and returns the previous one
	 * @param exakt the Element after the previous element
	 * @return the previous element
	 * @throws ElementNotFoundException
	 */
	
	private Elem<T> findPrevious(Elem<T> exakt) throws ElementNotFoundException{
		if(this.Size==1){
			count();
			return startElem.getNext();
		}
		Elem<T> elem = startElem;
		count();
		addStopAtEnd(exakt);
		count();
		while(elem.getNext()!=exakt){
			elem=elem.getNext();
			count();
		}
		if(elem==endElem &&elem.getNext()!=endElem){
			removeStopAtEnd();
			count();
			throw new ElementNotFoundException();
			
		}
		removeStopAtEnd();
		count();
		return elem;
	}

	/**
	 * Finds the position of an Element with a Stop element at the end of the list.
	 * 
	 * @param findElem
	 * @return position of the element that was looked for or (-1 if the Element is not in List)
	 * @throws ElementNotFoundException
	 */
	public Pos find(Elem findElem) {		
		if(this.Size==0){
			count();
			return new Pos(-1);
			
		}
		if(this.Size==1){
			count();
			return new Pos(0);
		}
		Elem elem = startElem;
		count();
		addStopAtEnd(findElem);
		count();
		Pos position = new Pos(0);
		count();
		while (!elem.equals(findElem)){	
			position.set(position.getPos()+1);//erh�he position um 1
			count();
			elem=elem.getNext(); //gehe ein elem weiter
			count();
	        }
		if(elem.getNext()==(endElem)){		
			removeStopAtEnd();
			count();
			position.set(-1);
			count();
			}
	    removeStopAtEnd();
	    count();
	    return position;
	}

	/**
	 * Returns the Element at a given at a given Position
	 * @param retrievePos position of the element in List
	 * @return the Element at a Position
	 * @throws IndexOutOfBoundsException
	 */
	public Elem<T> retrieve(Pos retrievePos) throws IndexOutOfBoundsException {
		if(retrievePos.getPos()>=Size){
			count();
			throw new IndexOutOfBoundsException();
		}
		Elem<T> elem = startElem;
		count();
		 Pos position = new Pos(-1);
		 count();
	        while (elem.getNext()!=endElem && position.getPos()!=retrievePos.getPos()){
	        	count();
	        	position.set(position.getPos()+1);//erh�he position um 1
	        	count();
	        	elem=elem.getNext(); //gehe ein elem weiter
	        }
	        count();
		return elem;
	}

	/**
	 * Concatenates this List with an other List
	 * @param otherList the List that you want to be concatenated
	 * @throws ElementNotFoundException 
	 */
	@Override
	public void concat(OwnList<T> otherList) {
		if(otherList.getClass().equals(this.getClass())){
			count();
			if(Size==0 && otherList.size()==0){
				count();
				return;
			}
			if(otherList.size()==0){
				count();
				return;
			}
			if(Size==0 && otherList.size()!=0){
				startElem = ((SingleLinkedList)otherList).getStartElem();
				count();
				endElem = ((SingleLinkedList)otherList).getEndElem();
				count();
				Size=otherList.size();
				count();
				return;
			}
			
			Elem elem =startElem;
			count();
			int counter =0;
			count();
			while(elem.getNext()!=(endElem)){
				
				elem = elem.getNext();
				count();
				counter++;
				count();
			}
			
			//setze den Zeiger dieses Elements auf das erste element der otherList nach dem start elem.
			
			elem.setNext(((SingleLinkedList)otherList).getStartElem().getNext());
			count();
			//�ndere das endElement auf das endElem der anderen Liste
			endElem=((SingleLinkedList)otherList).getEndElem();
			count();
			updateSize(Size, otherList.size());
			count();
		}else{
			count();
			//TODO 
		}
		
	}
	
	private void updateSize(int a, int b) {
		Size = a+b;	
		count();
	}
	
	
	
	/**
	 * Adds a Stop Element at the End
	 * @param newElem
	 */
	private void addStopAtEnd(Elem<T> newElem){
		Elem<T> elem = startElem;
		count();
		while(elem.getNext()!=endElem){
			elem=elem.getNext();
			count();
		}
		elem.setNext(newElem);
		count();
		newElem.setNext(endElem);
		count();
		
	}
	
	/**
	 * removes the Stop element at the end
	 */
	private void removeStopAtEnd(){
		Elem<T> elem = startElem;
		count();
		while(elem.getNext().getNext()!=endElem){
			elem = elem.getNext();
			count();
		}
		elem.setNext(endElem);
		count();
		
	}
	
	
	
	

	
	public Elem<T> getStartElem() {
		count();
		return startElem;
		
	}



	public void setStartElem(Elem<T> startElem) {
		count();
		this.startElem = startElem;
	}



	public Elem<T> getEndElem() {
		count();
		return endElem;
	}



	public void setEndElem(Elem<T> endElem) {
		count();
		this.endElem = endElem;
	}



	public int size() {
		count();
		// TODO Auto-generated method stub
		return Size;
	}
	
	private void count() {
        if(counter != null){
            counter.counterUp(1);
        }
    }


}
