import axios from '@/lib/axios';
import {DashboardStatsDto} from '@/types'

export const dashboardService = {
    getDashboardStats: async (): Promise<DashboardStatsDto> => {
        const response = await axios.get(`/dashboard/stats`);
        return response.data;
    }
}
