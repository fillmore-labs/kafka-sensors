package com.fillmore_labs.kafka.sensors.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.StateStore;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class MapKeyValueStore<K extends Object, V> implements KeyValueStore<K, V> {
  private final String name;
  private final Map<K, V> map;

  public MapKeyValueStore(String name) {
    this.name = name;
    this.map = new HashMap<>();
  }

  @Override
  public void put(K key, V value) {
    map.put(key, value);
  }

  @Override
  @SuppressWarnings("nullness:override.return") // KeyValueStore is not annotated
  public @Nullable V putIfAbsent(K key, V value) {
    return map.putIfAbsent(key, value);
  }

  @Override
  public void putAll(List<KeyValue<K, V>> entries) {
    entries.forEach(entry -> map.put(entry.key, entry.value));
  }

  @Override
  @SuppressWarnings("nullness:override.return") // KeyValueStore is not annotated
  public @Nullable V delete(K key) {
    return map.remove(key);
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  @SuppressWarnings("deprecation") // Override
  public void init(ProcessorContext context, StateStore root) {}

  @Override
  public void flush() {}

  @Override
  public void close() {}

  @Override
  public boolean persistent() {
    return false;
  }

  @Override
  public boolean isOpen() {
    return true;
  }

  @Override
  @SuppressWarnings("nullness:override.return") // KeyValueStore is not annotated
  public @Nullable V get(K key) {
    return map.get(key);
  }

  @Override
  public KeyValueIterator<K, V> range(K from, K to) {
    throw new UnsupportedOperationException();
  }

  @Override
  public KeyValueIterator<K, V> all() {
    throw new UnsupportedOperationException();
  }

  @Override
  public long approximateNumEntries() {
    return map.size();
  }
}
