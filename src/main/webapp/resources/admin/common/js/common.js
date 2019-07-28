(function($) {
    $.fn.extend({
        //时间选择器
        dateRange: function(){
            $(this).daterangepicker({
                autoUpdateInput:false,
                locale: {
                    format: 'YYYY-MM-DD',
                    applyLabel: '确定',
                    cancelLabel: '清空',
                    fromLabel: '起始时间',
                    toLabel: '结束时间',
                    customRangeLabel: '手动选择',
                    daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
                    monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
                        '七月', '八月', '九月', '十月', '十一月', '十二月'
                    ],
                    firstDay: 1
                }
            });
            $(this).on('apply.daterangepicker', function(ev, picker) {
                var startDate = picker.startDate.format('YYYY-MM-DD');
                var endDate = picker.endDate.format('YYYY-MM-DD');
                $(this).val(startDate + ' - ' + endDate);
                $('input[name="startDate"]').val(startDate);
                $('input[name="endDate"]').val(endDate);
            });
            $(this).on('cancel.daterangepicker', function(ev, picker) {
                $(this).val('');
                $('input[name="startDate"]').val('');
                $('input[name="endDate"]').val('');
            });
        }
    });
})(jQuery);