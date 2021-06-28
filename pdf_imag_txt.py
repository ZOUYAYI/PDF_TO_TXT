#命令行 先pdf->image 再image->txt
import os
from pdf2image import convert_from_path
def to_image(filename):
    im = convert_from_path('./'+filename+'.pdf')
    if not os.path.exists(filename):
        os.mkdir(filename)
    for i in range(len(im)):
        file_name = filename+'/'+filename+'_'+str(i)+'.png'
        print(file_name)
        im[i].save(file_name)

def to_text(file_dir):
    file_list = os.listdir(file_dir)
    total_text = ''
    file_num = len(file_list)
    if 'result.txt' in file_list:
        file_num-=1
    for i in range(file_num):
        temp_filepath = os.path.join(file_dir, file_dir+'_'+str(i)+'.png')
        print(temp_filepath)
        target_filename = file_dir + '/result'
        target_txt_filename = file_dir + '/result.txt'
        command = 'tesseract -l chi_sim --dpi 300 ' + temp_filepath + ' ' + target_filename + ' --psm 3'
        os.system(command)
        print(temp_filepath, ' ok')
        with open(target_txt_filename, 'r', encoding='utf-8') as txtfile:
            total_text += txtfile.read()
    with open('final_text_ch.txt','w',encoding='utf-8') as finaltext:
        finaltext.write(total_text)

if __name__ == '__main__':
    filename = 'UEBA'
    to_image(filename)
    to_text(filename)
    print('finish')
