
def compute_mean(input_data):
    sum = 0
    for x in input_data:
        sum += x
    return sum / len(input_data)


def compute_MAD(input_data):
    mean = compute_mean(input_data)
    sum = 0
    for x in input_data:
        sum += abs(x - mean)
    return sum / len(input_data)


def sort_HELPER(input_data):  # NEED TO MAKE PRIVATE
    res = []
    for x in range(1, len(input_data)):
        temp = input_data[x]
        possibleIndex = x
        while possibleIndex > 0 and temp < input_data[possibleIndex-1]:
            input_data[possibleIndex] = input_data[possibleIndex-1]
            possibleIndex = possibleIndex-1
        input_data[possibleIndex] = temp
    res = input_data
    return res


def compute_median(input_data):
    middleIndex = (int)(len(input_data) / 2)
    input_data = sort_HELPER(input_data)
    temp = 0

    if input_data % 2 != 0:
        temp = input_data[middleIndex]
    else:
        temp = (input_data[middleIndex-1] + input_data[middleIndex]) / 2.0

    return temp


def compute_minimum(input_data):
    min = input_data[0]
    for x in input_data:
        if x < min:
            min = x
    return min


def compute_maximum(input_data):
    max = input_data[0]
    for x in input_data:
        if x > max:
            max = x
    return max


def compute_range(input_data):
    return (compute_maximum(input_data) - compute_minimum(input_data))


def compute_variance(input_data):
    sum_diff = 0
    mean = compute_mean(input_data)
    for x in range(input_data):
        sum_diff += ((x - mean) ** 2)
    return sum_diff / (len(input_data)-1)


def compute_standard_deviation(input_data):
    return sqrt(compute_variance(input_data))


def compute_quartile1(input_data):
    middle_index = len(input_data) / 2
    count = -1
    quartile1 = 0
    input_data = sort_HELPER(input_data)

    for x in range(0, len(input_data)-1):
        count = count + 1

    if (len(input_data) % 2 == 0) and (count % 2 != 0):
        quartile1 = (input_data[count/2] + input_data[(count/2) + 1]) / 2

    if (len(input_data) % 2 == 0) and (count % 2 == 0):
        quartile1 = input_data[count/2]

    if (len(input_data) % 2 != 0) and (count % 2 == 0):
        quartile1 = input_data[count/2]

    if (len(input_data) % 2 != 0) and (count % 2 != 0):
        quartile1 = (input_data[count/2] + input_data[(count/2) + 1]) / 2

    return quartile1


def compute_quartile3(input_data):
    middle_index = len(input_data) / 2
    array_even_counter = 0
    array_odd_counter = 0
    quartile3 = 0
    input_data = sort_HELPER(input_data)

    for x in range(middle_index+1, len(input_data)):
        array_even_counter = array_even_counter+1

    if (len(input_data) % 2 != 0) and (array_even_counter % 2 != 0):
        quartile3 = input_data[middle_index + ((array_even_counter / 2) + 1)]

    if (len(input_data) % 2 != 0) and (array_even_counter % 2 == 0):
        quartile3 = (input_data[middle_index + (array_even_counter/2)] +
                     input_data[middle_index + (array_even_counter/2) + 1]) / 2

    for x in range(middle_index, len(input_data)):
        array_odd_counter = array_odd_counter + 1

    if (len(input_data) % 2 == 0) and (array_odd_counter % 2 != 0):
        quartile3 = input_data[middle_index + (array_odd_counter/2)]

    if (len(input_data) % 2 == 0) and (array_odd_counter % 2 == 0):
        quartile3 = (input_data[middle_index + (array_odd_counter/2)-1] +
                     input_data[middle_index + (array_odd_counter/2)]) / 2

    return quartile3
