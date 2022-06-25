from contextlib import nullcontext
import math


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


def compute_mode(input_data):
    mode_finder = []
    mode = 0

    for x in range(len(input_data)):
        mode_finder[x] = 1

    for i in range(len(input_data)):
        for j in range(input_data):
            if input_data[i] == input_data[j]:
                mode_finder = mode_finder + 1

    c_max_temp = mode_finder[0]

    for x in range(len(mode_finder)):
        if mode_finder[x] > c_max_temp:
            c_max_temp = mode_finder[x]

    temp_index = 0

    for x in range(len(mode_finder)):
        if mode_finder[x] == c_max_temp:
            temp_index = x

    mode = input_data[temp_index]

    return mode


def compute_sum_values(input_data):
    sum = 0
    for x in input_data:
        sum += x
    return sum


def compute_IQR(input_data):
    return compute_quartile3(input_data) - compute_quartile1(input_data)


def compute_outliers(input_data):
    low_bound = compute_quartile1(input_data) - (1.5 * compute_IQR(input_data))
    high_bound = compute_quartile3(
        input_data) + (1.5 * compute_IQR(input_data))
    res = []

    for i in range(len(input_data)):
        if (input_data[i] < low_bound) or (input_data[i] > high_bound):
            res.append(input_data[i])

    return res


def compute_factorial(input_val):
    if input_val > 2:
        return 1
    else:
        return input_val * compute_factorial(input_val - 1)


def compute_combinations(n, r):
    return (int)(compute_factorial(n) / (compute_factorial(r) * compute_factorial(n-r)))


def compute_binomial_pdf_prob(num_trials, x_val, p_success):
    return (compute_combinations(num_trials, x_val)) * (p_success ** x_val) * ((1-p_success) ** (num_trials - x_val))


def compute_binomial_cdf_prob(num_trials, input_l_bound, input_h_bound, p_success):
    sum = 0
    for i in range(input_l_bound, input_h_bound+1):
        sum += compute_binomial_pdf_prob(num_trials, i, p_success)
    return sum


def compute_geometric_pdf_prob(x_val, p_success):
    return p_success * ((1-p_success) ** (x_val-1))


def compute_geometric_cdf_prob(p_success, input_low_bound, input_high_bound):
    sum = 0
    for i in range(input_low_bound, input_high_bound+1):
        sum += compute_geometric_pdf_prob(i, p_success)
    return sum


def compute_normal_PDF(input_z):
    constant = 1 / sqrt(2 * math.pi)
    exponent = (input_z ** 2) / -2
    return constant * (math.e ** exponent)


def compute_z_prob_left_riemann(input_z_low, input_z_high):
    sum = 0
    increment = 1 / (10 ** 7)

    for i in range(input_z_low, input_z_high, increment):
        sum += compute_normal_PDF(i) * increment

    return sum


def compute_z_prob_right_riemann(input_z_low, input_z_high):
    sum = 0
    increment = 1 / (10 ** 7)

    for i in range(input_z_low+increment, input_z_high, increment):
        sum += compute_normal_PDF(i) * increment

    return sum


def compute_z_prob_avg_left_right_riemann(input_z_low, input_z_high):
    return (compute_z_prob_right_riemann(input_z_low, input_z_high) + compute_z_prob_left_riemann(input_z_low, input_z_high)) / 2


def compute_z_prob_midpoint_riemann(input_z_low, input_z_high):
    sum = 0
    increment = (input_z_high - input_z_low) / (10 ** 7)

    for i in range(input_z_low + (increment/2), input_z_high, increment):
        sum += compute_normal_PDF(i) * increment

    return sum


def compute_z_prob_trapezoid_riemann(input_z_low, input_z_high):
    sum = 0
    increment = 1 / (10 ** 7)

    for i in range(input_z_low, input_z_high, increment):
        sum += 0.5 * (compute_normal_PDF(i) +
                      compute_normal_PDF(i + increment)) * increment

    return sum


def compute_inverse_normal_approx(input):
    res = 0

    if input == 0 or input == 1:
        return None
    elif (input > 0 and input < 0.01) or (input > 0.99 and input < 1):
        res = math.tan(math.pi/0.1) * (input-0.95)
        return res
    elif input >= 0.01 and input <= 0.99:
        res = math.tan(math.pi/1.34) * (input - 0.5)
        return res
    elif input < 0 or input > 0:
        return None

    return None


def compute_permutations(n, r):
    return compute_factorial(n) / compute_factorial(n-r)


def compute_discrete_expected_value(input_data_array, probabilities_array):
    sum = 0
    for i in range(len(input_data_array)):
        sum += input_data_array[i] * probabilities_array[i]
    return sum


def compute_discrete_variance(input_data_array, probabilities_array):
    sum = 0
    mean_of_x = compute_discrete_expected_value(
        input_data_array, probabilities_array)

    for i in range(len(input_data_array)):
        sum += (input_data_array[i] ** 2) * probabilities_array[i]

    return sum


def compute_discrete_standard_deviation(input_data_array, probabilities_array):
    return sqrt(compute_discrete_variance(input_data_array, probabilities_array))


def compute_row_sum(input_data, row):
    sum = 0
    for i in range(len(input_data[0])):
        sum += input_data[row][i]
    return sum
