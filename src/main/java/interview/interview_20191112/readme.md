ArrayList集合为什么不能使用foreach增加 删除 修改元素

      示例参见ArrayListUpdateTest.java
      
      执行结果：
      arrayList1的remove方法成功执行，但是arrayList2的remove方法运行抛出ConcurrentModificationException异常。
    
      因为foreach的本质就是使用的迭代器Iterator,所有的Collection集合类都会实现Iterable接口。
      找到ArrayList类的iterator()方法
      
      public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }
        
        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
        
        解释不太清，还是自己走一遍断点吧。总的来说，就是add 和remove操作都会导致modCount增加和较少，导致
        modCount != expectedModCount, 所以不允许在foreach中删除、增加、修改ArrayList中的元素。
        
        
        推荐使用Iterator来删除元素，如果存在并发操作还要对Iterator进行加锁。
        
        Iterator<String> iterator = arrayList2.iterator();
        while(iterator.hasNext()){
            String item = iterator.next();
            if("1".equals(item)){
                iterator.remove();
               }
            }
        
        