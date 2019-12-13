package io.batcloud.utils;

import javafx.util.Pair;

import java.text.NumberFormat;
import java.util.*;

/**
 * 基于权重的选择算法
 * @author zhizhen
 *
 * @param <K>自定义对象
 * @param <V>权重
 */
public class WeightRandomUtil<K, V extends Number> {

	private TreeMap<Double, K> weightMap = new TreeMap<Double, K>();
	private HashMap<K,Integer> selfIndexMap = new HashMap<K, Integer>();
	private List<Pair<K, V>> list = new ArrayList<Pair<K,V>>();
	
	public WeightRandomUtil(List<Pair<K, V>> list) {
		this.list = new ArrayList<Pair<K,V>>(list);
		loadWeightMap();
	}

	public K random() {
		double randomWeight = this.weightMap.lastKey() * Math.random();
		SortedMap<Double, K> tailMap = this.weightMap.tailMap(randomWeight,
				false);
		return this.weightMap.get(tailMap.firstKey());

	}
	
	public K randomAndReload() {
		double randomWeight = this.weightMap.lastKey() * Math.random();
		SortedMap<Double, K> tailMap = this.weightMap.tailMap(randomWeight,
				false);
		K k = this.weightMap.get(tailMap.firstKey());
		reload(k);
		return k;

	}
	
	public void reload(K k){
		this.list.remove(selfIndexMap.get(k).intValue());
		reloadWeightMap();
	}
	
	public void reloadWeightMap(){
		//清空以前老数据
		this.weightMap.clear();
		this.selfIndexMap.clear();
		loadWeightMap();
	}
	
	public void loadWeightMap(){
		for (int i = 0;i<this.list.size();i++) {
			Pair<K, V> pair = this.list.get(i);
			double lastWeight = this.weightMap.size() == 0 ? 0 : this.weightMap
					.lastKey().doubleValue();// 统一转为double
			this.weightMap.put(pair.getValue().doubleValue() + lastWeight,
					pair.getKey());// 权重累加
			//自身位置对应值
			this.selfIndexMap.put(pair.getKey(), i);
		}
	}
	/*
	 * 权重随机获取对象
	 * count：获取个数
	 */
	public List<K> getModelList(Integer count) {
		List<K> list = new ArrayList<K>();
		
		if(weightMap.size()<=count){//总数大于需求数，返回全部，待处理
			return list;
		}
			
		for(int i = 0;i<count;i++) {
			list.add(randomAndReload());
		}
		return list;
	}
	
	public static void main(String[] args) {
		NumberFormat nf = NumberFormat.getInstance();

	}

}