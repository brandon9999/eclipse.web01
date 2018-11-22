package kr.co.hanbitbook.ejb.examples.shop;

public class ShoppingCart {
  private java.util.Collection list = null;
  public ShoppingCart(){
    list = new java.util.ArrayList();
  } // ShoppingCart

  public java.util.Collection getItems(){
        if(list.size() == 0)
                return null;
        return list;
  }

  public void addItem(ItemDataBean ibean){
    java.util.Iterator iter = list.iterator();
    boolean findflag = false;
    // 이미 쇼핑카트에 존재할 경우 개수만 증가
    while(iter.hasNext()){
      ItemDataBean temp = (ItemDataBean)iter.next();
      if(temp.getId().equals(ibean.getId())){
        temp.setCount(temp.getCount() + ibean.getCount());
        findflag = true;
        break;
      }
    } // while

    // 쇼핑카트에 존재하지 않을 경우 추가
    if(!findflag){
      list.add(ibean);
    }
  } // addItem

  public void deleteItem(String id){
    java.util.Iterator iter = list.iterator();
    boolean findflag = false;
    // 이미 쇼핑카트에 존재할 경우 해당 물품 삭제
    while(iter.hasNext()){
      ItemDataBean temp = (ItemDataBean)iter.next();
      if(temp.getId().equals(id)){
        list.remove(temp);
        break;
      }
    } // while
  } // deleteItem

  public void updateItem(String id, int count){
    java.util.Iterator iter = list.iterator();
    boolean findflag = false;
    // 이미 쇼핑카트에 존재할 경우 해당 물품의 수량의 변경
    while(iter.hasNext()){
      ItemDataBean temp = (ItemDataBean)iter.next();
      if(temp.getId().equals(id)){
        temp.setCount(count);
        break;
      }
    } // while
  } // deleteItem
}
