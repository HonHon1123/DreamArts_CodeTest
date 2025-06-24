import sys
from collections import defaultdict

#再起上限
sys.setrecursionlimit(10000)


graph = defaultdict(list)
nodes = set()

for line in sys.stdin:
    if not line.strip():
        continue
    parts = [x.strip() for x in line.strip().split(',')]
    if len(parts) != 3:
        continue
    u, v, w = int(parts[0]), int(parts[1]), float(parts[2])
    graph[u].append((v, w))
    nodes.add(u)
    nodes.add(v)

#最長の記録
longest_path = []
max_dist = 0.0


def dfs(current, visited, path, distance):
    global longest_path, max_dist
    visited.add(current)
    path.append(current)

    if distance > max_dist:
        max_dist = distance
        longest_path = path.copy()

    for neighbor, weight in graph[current]:
        if neighbor not in visited:
            dfs(neighbor, visited, path, distance + weight)

    path.pop()
    visited.remove(current)

#全部のノードの始点にする。
for start in nodes:
    dfs(start, set(), [], 0.0)


# 出力
for node in longest_path:
    print(node)
