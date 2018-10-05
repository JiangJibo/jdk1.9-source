/*
 * Copyright (c) 2000, 2001, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/*
 */

package sun.nio.ch;                                     // Formerly in sun.misc

// ## In the fullness of time, this class will be eliminated

class AllocatedNativeObject                             // package-private
    extends NativeObject {

    /**
     * 在Java Heap 之外分配一个至少多少字节的空间，并为此空间创建Native Object
     * Allocates a memory area of at least {@code size} bytes outside of the
     * Java heap and creates a native object for that area.
     *
     * @param size        Number of bytes to allocate
     * @param pageAligned 页对齐,是否在硬件层面进行页对齐, 也就是适配不同计算机的页大小
     *                    If {@code true} then the area will be aligned on a hardware
     *                    page boundary
     * @throws OutOfMemoryError If the request cannot be satisfied
     */
    AllocatedNativeObject(int size, boolean pageAligned) {
        super(size, pageAligned);
    }

    /**
     * Frees the native memory area associated with this object.
     */
    synchronized void free() {
        if (allocationAddress != 0) {
            unsafe.freeMemory(allocationAddress);
            allocationAddress = 0;
        }
    }

}
