package com.example.demo.java;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Patient
 *
 * @author wkk
 * @date 2023-11-27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    String name;
}
