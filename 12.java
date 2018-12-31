public int get(int a[]){
    int cnt = 0
    for(int i=0;i<a.length;i++){
        if(a[i]!=i) {
            int index = a[i];
            int temp = a[i];
            a[i] = a[index];
            a[index] = temp;
            cnt++;
        }
    }
    return cnt;
}
