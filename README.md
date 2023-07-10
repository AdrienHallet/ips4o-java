# Question
Is ips4o a good contender in the Java Sorting Algorithm space?

# Simple benchmark
| Algorithm                        | Execution Time (ms) |
|----------------------------------|---------------------|
| Java                             | 27                  |
| Java Parallel Array (PA)         | 6                   |
| IPS4o with traditional threads (T) | 40                  |
| IPS4o with Loom's virtual threads (VT) | 25              |
| IPS4o with improved Loom's virtual threads (IVT) | 13 |

# Answer
Perhaps, but my implementations of it are not (yet?)
