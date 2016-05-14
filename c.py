def sortz(nums):
	for i in xrange(0,len(nums) - 1):
		for j in xrange(0,len(nums) - 1 - i):
			if nums[j] > nums[j+1]:
				nums[j+1], nums[j] = nums[j], nums[j+1]
				print nums

	return nums

def cocktailSort(A):
    up = range(len(A)-1)
    while True:
        for indices in (up, reversed(up)):
            swapped = False
            for i in indices:
                if A[i] > A[i+1]:  
                    A[i], A[i+1] =  A[i+1], A[i]
                    swapped = True
            if not swapped:
                return
    return A

def insertion_sort(lst):
    if len(lst) == 1:
        return

    for i in xrange(1, len(lst)):
        temp = lst[i]

        j = i - 1

        while j >= 0 and temp < lst[j]:

            lst[j + 1] = lst[j]
            j -= 1
        print lst
        lst[j + 1] = temp

    return lst

def bubble_sort(list):
    length = len(list)
    for i in xrange(0,length - 1):
        for j in xrange(0,length - 1 - i):
            if list[j] > list[j+1]:
                list[j], list[j+1] = list[j+1], list[j]
                     
    return list


if __name__ == '__main__':
	a = [10, 4, 55, 2]
	c = bubble_sort(a)
	print c
 	





	