# Additional clean files
cmake_minimum_required(VERSION 3.16)

if("${CONFIG}" STREQUAL "" OR "${CONFIG}" STREQUAL "Debug")
  file(REMOVE_RECURSE
  "CMakeFiles/qtfull_autogen.dir/AutogenUsed.txt"
  "CMakeFiles/qtfull_autogen.dir/ParseCache.txt"
  "qtfull_autogen"
  )
endif()
