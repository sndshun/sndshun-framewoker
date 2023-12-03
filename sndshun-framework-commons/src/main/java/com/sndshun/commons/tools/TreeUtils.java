package com.sndshun.commons.tools;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 树形结构工具类
 * @author sndshun
 * @date 2023/11/28 03:04:03
 */

public class TreeUtils {




    /**
     * 将扁平的数据列表转换为树形结构
     *
     * @param flatList        扁平数据列表 集合
     * @param idExtractor     从节点中提取唯一标识的函数  id
     * @param parentExtractor 从节点中提取父节点标识的函数 父节点属性
     * @param childrenExtractor 从节点中提取子节点标识的函数 字节点属性
     * @param comparator 排序规则
     * @param <T>             节点类型
     * @return 树形结构的根节点列表
     * @author sndshun
     * @date 2023-11-28 06:53:07
     */
    public static <T> List<T> treeParent(List<T> flatList, Function<T, ?> idExtractor,
                                        Function<T, ?> parentExtractor,
                                        Function<T, List<T>> childrenExtractor,
                                        Comparator<T> comparator) {

        return buildTree(flatList, idExtractor, parentExtractor, childrenExtractor, comparator);
    }

    /**
     * 将扁平的数据列表转换为树形结构
     *
     * @param flatList        扁平数据列表 集合
     * @param idExtractor     从节点中提取唯一标识的函数  id
     * @param parentExtractor 从节点中提取父节点标识的函数 父节点属性
     * @param childrenExtractor 从节点中提取子节点标识的函数 字节点属性
     * @param <T>             节点类型
     * @return 树形结构的根节点列表
     * @author sndshun
     * @date 2023-11-28 06:53:07
     */
    public static <T> List<T> treeParent(List<T> flatList, Function<T, ?> idExtractor,
                                         Function<T, ?> parentExtractor,
                                         Function<T, List<T>> childrenExtractor) {

        return buildTree(flatList, idExtractor, parentExtractor, childrenExtractor, null);
    }
    /**
     * 将扁平的数据列表转换为树形结构
     *
     * @param flatList        扁平数据列表 集合
     * @param idExtractor     从节点中提取唯一标识的函数  id
     * @param parentExtractor 从节点中提取父节点标识的函数 父节点属性
     * @param childrenExtractor 从节点中提取子节点标识的函数 字节点属性
     * @param comparator 排序规则
     * @param <T>             节点类型
     * @return 树形结构的根节点列表
     * @author sndshun
     * @date 2023-11-28 06:53:07
     */
    private static <T> List<T> buildTree(List<T> flatList, Function<T, ?> idExtractor,
                                        Function<T, ?> parentExtractor,
                                        Function<T, List<T>> childrenExtractor,
                                        Comparator<T> comparator) {
        if (null != comparator) {
            flatList = flatList.stream().sorted(comparator).collect(Collectors.toList());
        }

        // 使用 Java 8 的 Stream API 将扁平列表按照父节点分组
        Map<String, List<T>> groupedByParentId = flatList.stream()
                .collect(Collectors.groupingBy(parent -> {
                    Object parentId = parentExtractor.apply(parent);
                    return null == parentId ? "0" : parentId.toString();
                }, Collectors.toList()));


        // 获取顶层节点（没有父节点的节点）
        List<T> roots = groupedByParentId.get("0");

        // 如果存在顶层节点，则递归地添加子节点
        if (roots != null) {
            roots.forEach(root -> addChildNodes(root, groupedByParentId, idExtractor, childrenExtractor));
        }

        return roots;
    }

    /**
     * 递归地添加子节点
     *
     * @param node              当前节点
     * @param groupedByParentId 按照父节点分组的映射
     * @param idExtractor       从节点中提取唯一标识的函数
     * @param <T>               节点类型
     * @author sndshun
     * @date 2023-11-28 06:53:22
     */
    private static <T> void addChildNodes(T node, Map<String, List<T>> groupedByParentId, Function<T, ?> idExtractor, Function<T, List<T>> childrenExtractor) {
        // 从映射中获取当前节点的子节点
        List<T> children = groupedByParentId.get(idExtractor.apply(node).toString());

        // 如果存在子节点，则递归地添加子节点的子节点
        if (children != null) {
            childrenExtractor.apply(node).addAll(children);

            children.forEach(child -> addChildNodes(child, groupedByParentId, idExtractor, childrenExtractor));
        }
    }

}
