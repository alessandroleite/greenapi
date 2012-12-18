#!/bin/bash
# by Paul Colby (http://colby.id.au), no rights reserved ;)
 
PREV_TOTAL=0
PREV_IDLE=0

CPU_NR=(`ls /sys/devices/system/cpu | grep 'cpu[0-9]' | wc -l`)
cpu=$CPU_NR
echo "CPU No.$cpu"
while true; do
  CPU=(`cat /proc/stat | grep '^cpu '`) # Get the total CPU statistics.
  unset CPU[0]                          # Discard the "cpu" prefix.
  IDLE=${CPU[4]}                        # Get the idle CPU time.
 
  # Calculate the total CPU time.
  TOTAL=0
  for VALUE in "${CPU[@]}"; do
    let "TOTAL=$TOTAL+$VALUE"
  done
 
  # Calculate the CPU usage since we last checked.
  let "DIFF_IDLE=$IDLE-$PREV_IDLE"
  let "DIFF_TOTAL=$TOTAL-$PREV_TOTAL"
  let "DIFF_USAGE=(1000*($DIFF_TOTAL-$DIFF_IDLE)/$DIFF_TOTAL+5)/10"
   
  if [ $cpu -ge $CPU_NR ]; then
	 let "cpu=$CPU_NR-1"
  fi

  if [ $cpu -gt 0 ]; then
	 if [ $DIFF_USAGE -lt 50 ]; then
		if [ "`cat /sys/devices/system/cpu/cpu$cpu/online`" == "1" ];
		then		
			echo 0 > /sys/devices/system/cpu/cpu$cpu/online
		fi	 	
		let "cpu=$cpu-1"
	 else
		if [ "`cat /sys/devices/system/cpu/cpu$cpu/online`" == "0" ];
		then		
			echo 1 > /sys/devices/system/cpu/cpu$cpu/online
		fi
		let "cpu=$CPU_NR+1"
     fi
  fi

  # Remember the total and idle CPU times for the next check.
  PREV_TOTAL="$TOTAL"
  PREV_IDLE="$IDLE" 
  echo -en "\rLoad of system:$DIFF_USAGE%, No. CPU online: `cat /proc/stat | grep '^cpu[0-9]' | wc -l`  \b\b"
  # Wait before checking again.
  sleep 0.5
done
