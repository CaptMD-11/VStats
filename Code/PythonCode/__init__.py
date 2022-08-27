from contextlib import nullcontext
import math
import numpy as np


def compute_mean(input_data):
    sum = 0
    for x in range(len(input_data)):
        sum = (sum + input_data[x])
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

    if len(input_data) % 2 != 0:
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
    for x in input_data:
        sum_diff += ((x - mean) ** 2)
    return sum_diff / (len(input_data)-1)


def compute_standard_deviation(input_data):
    return math.sqrt(compute_variance(input_data))


def compute_quartile1(input_data):
    middle_index = math.trunc(len(input_data) / 2)
    count = -1
    quartile1 = 0
    input_data = sort_HELPER(input_data)

    for i in range(middle_index):
        count += 1

    if (len(input_data) % 2 == 0) and (count % 2 != 0):
        quartile1 = (input_data[(math.trunc(count/2))] +
                     input_data[(math.trunc(count/2)) + 1]) / 2.0

    if (len(input_data) % 2 == 0) and (count % 2 == 0):
        quartile1 = input_data[math.trunc(count/2)]

    if (len(input_data) % 2 != 0) and (count % 2 == 0):
        quartile1 = input_data[math.trunc(count/2)]

    if (len(input_data) % 2 != 0) and (count % 2 != 0):
        quartile1 = (input_data[math.trunc(count/2)] +
                     input_data[math.trunc(count/2) + 1]) / 2.0

    return quartile1


def compute_quartile3(input_data):
    middle_index = math.trunc(len(input_data) / 2)
    array_even_counter = 0
    array_odd_counter = 0
    quartile3 = 0
    input_data = sort_HELPER(input_data)

    for x in range(middle_index+1, len(input_data)):
        array_even_counter = array_even_counter+1

    if (len(input_data) % 2 != 0) and (array_even_counter % 2 != 0):
        quartile3 = input_data[middle_index +
                               math.trunc((array_even_counter / 2) + 1)]

    if (len(input_data) % 2 != 0) and (array_even_counter % 2 == 0):
        quartile3 = (input_data[middle_index + math.trunc(array_even_counter/2)] +
                     input_data[middle_index + math.trunc(array_even_counter/2) + 1]) / 2

    for x in range(middle_index, len(input_data)):
        array_odd_counter = array_odd_counter + 1

    if (len(input_data) % 2 == 0) and (array_odd_counter % 2 != 0):
        quartile3 = input_data[middle_index + math.trunc(array_odd_counter/2)]

    if (len(input_data) % 2 == 0) and (array_odd_counter % 2 == 0):
        quartile3 = (input_data[middle_index + math.trunc(array_odd_counter/2)-1] +
                     input_data[middle_index + math.trunc(array_odd_counter/2)]) / 2

    return quartile3


def compute_mode(input_data):
    mode_finder = []
    mode = 0

    for x in range(len(input_data)):
        mode_finder.append(1)

    for i in range(len(input_data)):
        for j in range(len(input_data)):
            if input_data[i] == input_data[j]:
                mode_finder[i] = mode_finder[i] + 1

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
    if input_val < 2:
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
    constant = 1 / math.sqrt(2 * math.pi)
    exponent = (input_z ** 2) / -2
    return constant * (math.e ** exponent)


def compute_z_prob_left_riemann(input_z_low, input_z_high):
    sum = 0
    increment = 1 / (10 ** 7)

    for i in np.arange(input_z_low, input_z_high, increment):
        sum += compute_normal_PDF(i) * increment

    return sum


def compute_z_prob_right_riemann(input_z_low, input_z_high):
    sum = 0
    increment = 1 / (10 ** 7)

    for i in np.arange(input_z_low+increment, input_z_high, increment):
        sum += compute_normal_PDF(i) * increment

    return sum


def compute_z_prob_avg_left_right_riemann(input_z_low, input_z_high):
    return (compute_z_prob_right_riemann(input_z_low, input_z_high) + compute_z_prob_left_riemann(input_z_low, input_z_high)) / 2


def compute_z_prob_midpoint_riemann(input_z_low, input_z_high):
    sum = 0
    increment = (input_z_high - input_z_low) / (10 ** 7)

    for i in np.arange(input_z_low + (increment/2), input_z_high, increment):
        sum += compute_normal_PDF(i) * increment

    return sum


def compute_z_prob_trapezoid_riemann(input_z_low, input_z_high):
    sum = 0
    increment = 1 / (10 ** 7)

    for i in np.arange(input_z_low, input_z_high, increment):
        sum += 0.5 * (compute_normal_PDF(i) +
                      compute_normal_PDF(i + increment)) * increment

    return sum


def compute_inverse_normal_approx(input):
    res = 0

    if input == 0 or input == 1:
        return None
    elif (input > 0 and input < 0.01) or (input > 0.99 and input < 1):
        res = math.tan((math.pi/0.1) * (input-0.95))
        return res
    elif input >= 0.01 and input <= 0.99:
        res = math.tan((math.pi/1.34) * (input - 0.5))
        return res
    elif input <= 0 or input >= 1:
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
        sum += ((input_data_array[i] - mean_of_x)
                ** 2) * probabilities_array[i]

    return sum


def compute_discrete_standard_deviation(input_data_array, probabilities_array):
    return math.sqrt(compute_discrete_variance(input_data_array, probabilities_array))


def compute_row_sum(input_data, row):
    sum = 0
    for i in range(len(input_data[0])):
        sum += input_data[row][i]
    return sum


def compute_column_sum(input_data, col):
    sum = 0
    for i in range(len(input_data)):
        sum += input_data[i][col]
    return sum


def compute_row_product(input_data, row):
    product = 1
    for i in range(len(input_data[0])):
        product *= input_data[row][i]
    return product


def compute_column_product(input_data, col):
    product = 1
    for i in range(len(input_data)):
        product *= input_data[i][col]
    return product


def compute_matrix_addition(arr1, arr2):
    res = [[0 for x in range(len(arr1))] for i in range(len(arr1[0]))]
    for i in range(len(arr1)):
        for j in range(len(arr1[i])):
            res[i][j] = arr1[i][j] + arr2[i][j]
    return res


def compute_matrix_subtraction(arr1, arr2):
    res = [[0 for x in range(len(arr1))] for i in range(len(arr1[0]))]
    for i in range(len(arr1)):
        for j in range(len(arr1[i])):
            res[i][j] = arr1[i][j] - arr2[i][j]
    return res


def compute_matrix_multiplication_by_scalar(arr, scalar):
    res = [[0 for x in range(len(arr))] for i in range(len(arr[0]))]
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            res[i][j] = scalar * arr[i][j]
    return res

########################################


def compute_Se(ind_var, dep_var):
    return math.sqrt(compute_sum_of_residuals_squared(ind_var, dep_var) / (len(dep_var)-2))


def compute_sum_of_residuals_squared(ind_var, dep_var):
    sum = 0
    temp = compute_residual_values(ind_var, dep_var)

    for i in range(len(temp)):
        sum += (temp[i] ** 2)

    return sum


def compute_residual_values(ind_var, dep_var):
    res = []
    for i in range(len(ind_var)):
        res.append(
            dep_var[i] - compute_LSRL_output(ind_var, dep_var, ind_var[i]))
    return res


def compute_Y_predicted_values(ind_var, dep_var):
    res = []
    for i in range(len(ind_var)):
        res.append(compute_LSRL_output(ind_var, dep_var, ind_var[i]))
    return res


def compute_LSRL_output(ind_var, dep_var, input):
    return compute_A(ind_var, dep_var) + (compute_B(ind_var, dep_var) * input)


def display_LSRL_equation(ind_var, dep_var):
    count_ind = 0
    count_dep = 0
    for i in ind_var:
        if i != 0:
            count_ind += 1
    for j in dep_var:
        if j != 0:
            count_dep += 1
    if count_ind == 0 or count_dep == 0:
        return "Error - one or more variables contains all zeros"
    res = "ŷ = " + str(compute_A(ind_var, dep_var)) + " + " + \
        str(compute_B(ind_var, dep_var)) + "x"

    if compute_B(ind_var, dep_var) < 0:
        res = "ŷ = " + str(compute_A(ind_var, dep_var)) + \
            " - " + (str(-1 * compute_B(ind_var, dep_var))) + "x"
    elif compute_B(ind_var, dep_var) >= 0:
        res = "ŷ = " + str(compute_A(ind_var, dep_var)) + \
            " + " + str(compute_B(ind_var, dep_var)) + "x"

    return res


def compute_A(ind_var, dep_var):
    return (compute_mean(dep_var) - compute_B(ind_var, dep_var) * compute_mean(ind_var))


def compute_B(ind_var, dep_var):
    r = compute_R(ind_var, dep_var)
    b = r * (compute_standard_deviation(dep_var) /
             compute_standard_deviation(ind_var))
    return b


def compute_R(ind_var, dep_var):
    sum_of_products = 0

    arr1_bar = compute_mean(ind_var)
    arr1_sigma = compute_standard_deviation(ind_var)

    arr2_bar = compute_mean(dep_var)
    arr2_sigma = compute_standard_deviation(dep_var)

    for i in range(len(ind_var)):
        sum_of_products += (((ind_var[i] - arr1_bar) / arr1_sigma) *
                            ((dep_var[i] - arr2_bar) / arr2_sigma))

    return sum_of_products / (len(ind_var)-1)


def compute_R_squared(ind_var, dep_var):
    return (compute_R(ind_var, dep_var) ** 2)


def compute_z_star(input_confidence_level):
    inv_norm_input = (input_confidence_level +
                      ((1-input_confidence_level) / (2)))
    return compute_inverse_normal_approx(inv_norm_input)


def compute_one_mean_z_conf_int(mu, sigma, sample_size, confidence_level):

    low_bound = mu - (compute_z_star(confidence_level)
                      * (sigma / math.sqrt(sample_size)))

    high_bound = mu + (compute_z_star(confidence_level)
                       * (sigma / math.sqrt(sample_size)))

    return "(" + str(low_bound) + ", " + str(high_bound) + ")"


def compute_one_mean_z_test_Ha_greater_than_value(mu, sigma, sample_mean, sample_size, alpha):

    z_critical = (sample_mean - mu) / (sigma / math.sqrt(sample_size))

    p_value = compute_z_prob_midpoint_riemann(z_critical, 1000.0)

    if p_value < alpha:
        return "There is statistically significant evidence that Ha > H0... reject H0 - p-value: " + str(p_value)
    elif p_value > alpha:
        return "There is no statistically significant evidence that Ha > H0... fail to reject H0 - p-value: " + str(p_value)
    else:
        return ""


def compute_one_mean_z_test_Ha_less_than_value(mu, sigma, sample_mean, sample_size, alpha):

    z_critical = (sample_mean - mu) / (sigma / math.sqrt(sample_size))

    p_value = compute_z_prob_midpoint_riemann(-1000.0, z_critical)

    if p_value < alpha:
        return "There is statistically significant evidence that Ha < H0... reject H0 - p-value: " + str(p_value)
    elif p_value > alpha:
        return "There is no statistically significant evidence that Ha < H0... fail to reject H0 - p-value: " + str(p_value)
    else:
        return ""


def compute_one_mean_z_test_Ha_not_equal_to_value(mu, sigma, sample_mean, sample_size, alpha):

    z_critical = (sample_mean - mu) / (sigma / math.sqrt(sample_size))

    p_value = 2.0 * compute_z_prob_midpoint_riemann(abs(z_critical), 1000.0)

    if p_value < alpha:
        return "There is statistically significant evidence that Ha ≠ H0... reject H0 - p-value: " + str(p_value)
    elif p_value > alpha:
        return "There is no statistically significant evidence that Ha ≠ H0... fail to reject H0 - p-value: " + str(p_value)
    else:
        return ""


def compute_one_prop_z_conf_int(p_hat, sample_size, confidence_level):
    q_hat = 1-p_hat
    z_star = compute_z_star(confidence_level)
    standard_error = math.sqrt((p_hat * q_hat) / sample_size)

    low = p_hat - (z_star * standard_error)
    high = p_hat + (z_star * standard_error)

    return "(" + str(low) + ", " + str(high) + ")"


def compute_one_prop_z_test_P0_less_than_value(p_hat, p_nought, sample_size, alpha):
    q_nought = 1-p_nought
    z_critical = ((p_hat-p_nought) /
                  (math.sqrt((p_nought*q_nought) / sample_size)))
    p_value = compute_z_prob_midpoint_riemann(-1000.0, z_critical)

    if p_value < alpha:
        return "There is statistically significant evidence that the true P0 < given P0... reject H0 - p-value: " + str(p_value)
    elif p_value > alpha:
        return "There is no statistically significant evidence that the true P0 < given P0... fail to reject H0 - p-value: " + str(p_value)
    else:
        return ""


def compute_one_prop_z_test_P0_greater_than_value(p_hat, p_nought, sample_size, alpha):
    q_nought = 1-p_nought
    z_critical = ((p_hat-p_nought) /
                  (math.sqrt((p_nought * q_nought) / sample_size)))
    p_value = compute_z_prob_midpoint_riemann(z_critical, 1000.0)

    if p_value < alpha:
        return "There is statistically significant evidence that the true P0 > given P0... reject H0 - p-value: " + str(p_value)
    elif p_value > alpha:
        return "There is no statistically significant evidence that the true P0 > given P0... fail to reject H0 - p-value: " + str(p_value)
    else:
        return ""


def compute_one_prop_z_test_P0_not_equal_to_value(p_hat, p_nought, sample_size, alpha):
    q_nought = 1-p_nought
    z_critical = ((p_hat-p_nought) /
                  (math.sqrt((p_nought * q_nought) / sample_size)))
    p_value = compute_z_prob_midpoint_riemann(abs(z_critical), 1000.0) * 2.0

    if p_value < alpha:
        return "There is statistically significant evidence that the true P0 ≠ given P0... reject H0 - p-value: " + str(p_value)
    elif p_value > alpha:
        return "There is no statistically significant evidence that the true P0 ≠ given P0... fail to reject H0 - p-value: " + str(p_value)
    else:
        return ""


def compute_two_prop_z_test_P1_less_than_P2(successes1, sample_size1, successes2, sample_size2, alpha):
    p_hat1 = successes1 / sample_size1
    p_hat2 = successes2 / sample_size2

    p_hat_pooled = (successes1+successes2) / (sample_size1+sample_size2)
    q_hat_pooled = 1-p_hat_pooled

    z = (p_hat1-p_hat2) / math.sqrt(((p_hat_pooled*q_hat_pooled) /
                                     sample_size1) + ((p_hat_pooled*q_hat_pooled) / sample_size2))

    method_p_value = compute_z_prob_midpoint_riemann(-1000.0, z)

    if method_p_value < alpha:
        return "There is statistically significant evidence that the true P1 < P2... reject H0 - p-value: " + str(method_p_value)
    elif method_p_value > alpha:
        return "There is no statistically significant evidence that the true P1 < P2... fail to reject H0 - p-value: " + str(method_p_value)
    else:
        return ""


def compute_two_prop_z_test_P1_greater_than_P2(successes1, sample_size1, successes2, sample_size2, alpha):
    p_hat1 = successes1 / sample_size1
    p_hat2 = successes2 / sample_size2

    p_hat_pooled = (successes1+successes2) / (sample_size1+sample_size2)
    q_hat_pooled = 1-p_hat_pooled

    z = (p_hat1-p_hat2) / math.sqrt(((p_hat_pooled*q_hat_pooled) /
                                     sample_size1) + ((p_hat_pooled*q_hat_pooled) / sample_size2))

    method_p_value = compute_z_prob_midpoint_riemann(z, 1000.0)

    if method_p_value < alpha:
        return "There is statistically significant evidence that the true P1 > P2... reject H0 - p-value: " + str(method_p_value)
    elif method_p_value > alpha:
        return "There is no statistically significant evidence that the true P1 > P2... fail to reject H0 - p-value: " + str(method_p_value)
    else:
        return ""


def compute_two_prop_z_test_P1_not_equal_to_P2(successes1, sample_size1, successes2, sample_size2, alpha):
    p_hat1 = successes1 / sample_size1
    p_hat2 = successes2 / sample_size2

    p_hat_pooled = (successes1+successes2) / (sample_size1+sample_size2)
    q_hat_pooled = 1-p_hat_pooled

    z = (p_hat1-p_hat2) / math.sqrt(((p_hat_pooled*q_hat_pooled) /
                                     sample_size1) + ((p_hat_pooled*q_hat_pooled) / sample_size2))

    method_p_value = compute_z_prob_midpoint_riemann(abs(z), 1000.0) * 2.0

    if method_p_value < alpha:
        return "There is statistically significant evidence that the true P1 ≠ P2... reject H0 - p-value: " + str(method_p_value)
    elif method_p_value > alpha:
        return "There is no statistically significant evidence that the true P1 ≠ P2... fail to reject H0 - p-value: " + str(method_p_value)
    else:
        return ""


print(compute_one_prop_z_conf_int(0.7, 100, 0.95))
