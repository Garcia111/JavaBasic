快速排序：
    **【快速排序算法与傅里叶变换等算法 并称为二十世纪十大算法】**
    快速排序在每一轮挑选一个基准元素，并让其他比它大的元素移动到数列的一边，比它小的元素移动到数列的另一边，
    从而将数列拆解成了两个部分。
    平均情况下需要logN轮排序，每轮排序需要将N个元素进行比较，因此快速排序算法的平均时间复杂度是O(NlogN)，最坏情况
    下的时间复杂度是O(N^2);

  基准元素的选择
     1.最简单的方式是选择数列的第一个元素。
        缺点：这种选择在绝大多数情况下没有问题，但是假如有一个原本逆序的数列，如果仍然选择第一个元素作为基准元素，
            则排序将要进行N轮，时间复杂度退化为O(N^2)
        克服此缺点的方法是排序之前首先将数组打乱
     2.随机选择一个元素作为基准元素；
        即使是随机选择基准元素，每一次也有极小的概率会选到数列的最大值或最小值，同样会影响效果。

  容易出现的问题：
  1.没有考虑到数组中可能包含和切分元素的值相同的其他元素；
  2.处理切分元素值有重复的情况：
        左侧扫描最好是在遇到大于等于切分元素值的元素时停下，右侧扫描则是遇到小于等于切分元素值的元素时停下。
        尽管这样可能会不必要地将一些等值的元素交换，但是在某些典型应用中，它能够避免算法的运行时间变为平方级别。

  特点：
        1.快速排序是原地排序，只需要一个很小的辅助栈；
        2.将长度为N的数组排序所需的时间与NlogN成正比；
  缺点：
        非常脆弱，在实现时要非常小心才能避免低劣的性能。


    快速排序是一种分治的排序算法，它将一个数组分成两个子数组，将两部分独立排序。
    快速排序与归并排序是互补的：
    1.归并排序将数组分成两个数组分别排序，并将有序的子数组归并以将整个数组排序；归并排序中，一个数组被等分位两半；
    2.快速排序将数组排序的方式是 当两个子数组都有序的时候整个数组也就自然有序了；快速排序中，切分的位置取决于数组的内容



对于快速排序的性能提升改进：
    1.切换到插入排序
    **对于小数组，快速排序比插入排序慢, 因为快速排序的sort()在小数组中也会调用自己**：
    改进：

    public static <T extends Comparable<? super T>> void sort(T[] array, int startIndex, int endIndex){
            while (startIndex >= endIndex){
                return;
            }
            int pivotIndex = partion(array,startIndex, endIndex);

            //对数组分成的两个子数组进行排序
            sort(array,startIndex,pivotIndex-1);
            sort(array,pivotIndex+1,endIndex);
        }

    修改为：
    public static <T extends Comparable<? super T>> void sort(T[] array, int startIndex, int endIndex){
                while (startIndex+M >= endIndex){//当数组长度小于M时，使用插入排序，一般取值为5~15
                    InsertSort.sort(arary,startIndex,endIndex);
                    return;
                }
                int pivotIndex = partion(array,startIndex, endIndex);

                //对数组分成的两个子数组进行排序
                sort(array,startIndex,pivotIndex-1);
                sort(array,pivotIndex+1,endIndex);
            }

    2.三样取分；
    3.熵最优排序；












