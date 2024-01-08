def set_diff():
    zbior1 = set([1, 2, 3, 4, 5])
    zbior2 = set([1, 2, 3])
    return zbior1.difference(zbior2)


def get_odd():
    list = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    odd_nums = [x for x in list if x % 2 != 0]
    return odd_nums


def get_dict():
    dict = {'a': 1, 'b': 2, 'c': 3}
    return dict


def test_main():
    assert set_diff() == {4, 5}
    assert set_diff() != {4, 7}
    assert set_diff() != 5

    assert get_odd() == [1, 3, 5, 7, 9]
    assert get_odd() != [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    assert get_odd() != [2, 4, 6, 8, 10]

    assert get_dict() == {'a': 1, 'b': 2, 'c': 3}
    assert get_dict() != {'a': 1, 'b': 2, 'c': 3, 'd': 4}
    assert get_dict() != {'a': 1, 'b': 2}
